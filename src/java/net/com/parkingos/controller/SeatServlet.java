package net.com.parkingos.controller;

import com.alibaba.fastjson.JSON;
import net.com.parkingos.bean.ResultData;
import net.com.parkingos.bean.Seat;
import net.com.parkingos.dao.SeatDao;
import net.com.parkingos.service.SeatService;
import net.com.parkingos.service.SeatServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @date 2020/4/15
 */

@WebServlet("/seat")
public class SeatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method.equals("addSeat")) {
            //添加车位信息
            addSeat(req, resp);
        }
        if (method.equals("queryAllSeat")) {
            //查找所有信息
            queryAllSeat(req, resp);
        }
        if (method.equals("delSeat")) {
            //删除车位信息
            delSeat(req, resp);
        }
        if (method.equals("findSeat")) {
            //查找特定车位
            findSeat(req,resp);
        }
        if (method.equals("updateSeat")){
            //更新车位信息
            updateSeat(req,resp);
        }

    }

    private void updateSeat(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html;charset=utf-8");
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //1获取页面数据
        Map<String, String[]> map = req.getParameterMap();
        Seat seat=new Seat();
        try {
            //2转化
            BeanUtils.populate(seat,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(seat);
        //3调用服务层
        SeatService service=new SeatServiceImpl();
        boolean result = service.updateSeatId(seat);
        ResultData rs=null;

        if (result){
            //修改成功,回写数据
            rs=new ResultData(400,"修改成功");

        }else {
            rs=new ResultData(401,"修改失败");
        }

        String s = JSON.toJSONString(rs);
        try {
            //回传数据
            resp.getWriter().write(s);
            System.out.println("回写成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findSeat(HttpServletRequest req, HttpServletResponse resp) {
        //获取数据
        String id = req.getParameter("id");
        SeatService service =new SeatServiceImpl();
        Seat seat = service.findSeat(id);
        System.out.println(seat);
        if (seat!=null){
            //请求转发共享数据
            try {
                req.setAttribute("seat",seat);
                req.getRequestDispatcher("/Admin/SeatEdit.jsp").forward(req,resp);
                System.out.println("转发中..");
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println();
        }


    }


    private void delSeat(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        //获取信息
        String id = req.getParameter("id");

        //调用服务层
        SeatService service = new SeatServiceImpl();
        boolean result = service.delSeat(id);
        ResultData rs = null;

        if (result) {
            //删除成功
            rs = new ResultData(400, "删除成功!");
        } else {
            //删除失败
            rs = new ResultData(401, "删除失败");
        }

        String s = JSON.toJSONString(rs);
        try {
            //回写数据
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void queryAllSeat(HttpServletRequest req, HttpServletResponse resp) {

        //调用服务层
        SeatService service = new SeatServiceImpl();
        List<Seat> seats = service.queryAllSeat();

        if (seats != null) {
            //共享数据请求转发
            req.setAttribute("seats", seats);
            try {

                req.getRequestDispatcher("/Admin/SeatMsg.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("queryAllSeat有误..");
        }


    }

    private void addSeat(HttpServletRequest req, HttpServletResponse resp) {

        //设置编码
        resp.setContentType("text/html;charset=utf-8");

        //获取数据
        Map<String, String[]> map = req.getParameterMap();
        //获取实体类
        Seat carseat = new Seat();
        try {
            //转化
            BeanUtils.populate(carseat, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //新增车位,补全id及状态
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String seat_id = dateFormat.format(new Date());
        carseat.setSeat_id(seat_id);
        carseat.setSeat_state(0);

        //调用服务层
        SeatService service = new SeatServiceImpl();
        boolean b = service.addSeat(carseat);
        ResultData rs = null;
        if (b) {
            //添加成功,回写数据
            rs = new ResultData(400, "添加成功");
        } else {
            //添加失败.回写数据
            rs = new ResultData(401, "添加失败");
        }

        //将对象转化成json数据反馈
        String s = JSON.toJSONString(rs);
        try {
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}

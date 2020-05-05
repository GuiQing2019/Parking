package net.com.parkingos.controller;

import com.alibaba.fastjson.JSON;
import net.com.parkingos.bean.Card;
import net.com.parkingos.bean.ResultData;
import net.com.parkingos.bean.Seat;
import net.com.parkingos.service.CardService;
import net.com.parkingos.service.CardServiceImpl;
import net.com.parkingos.service.SeatService;
import net.com.parkingos.service.SeatServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @date 2020/4/18
 */
@WebServlet("/card")
public class CardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        if (method.equals("findSeat")) {
            //查找所有车位信息
            findSeat(req, resp);
        }
        if (method.equals("addCard")) {
            //添加IC卡信息
            addCard(req, resp);
        }
        if (method.equals("queryAllCard")) {
            //查找所有IC卡信息
            queryAllCard(req, resp);
        }
        if (method.equals("findCard")) {
            //查找特定IC卡信息
            findCard(req, resp);
        }
        if (method.equals("updateCard")) {
            //更新IC卡信息
            updateCard(req,resp);
        }
        if (method.equals("delCard")){
            //删除IC卡信息
            delCard(req,resp);
        }
    }

    private void delCard(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        CardService service=new CardServiceImpl();
        boolean result = service.delCard(id);
        ResultData rs=null;
        if (result) {
            //删除成功
            rs=new ResultData(400,"删除成功!");
        }else {
            rs=new ResultData(401,"删除失败!");
        }
        //转换,回传
        String s = JSON.toJSONString(rs);
        try {
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateCard(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        Map<String, String[]> ma = req.getParameterMap();
        Card card=new Card();

    }

    /**
     * 查找特定IC卡信息
     *
     * @param req
     * @param resp
     */
    private void findCard(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");

        CardService service = new CardServiceImpl();


        Card card = service.findCard(id);

        req.setAttribute("card", card);
        try {
            req.getRequestDispatcher("/Admin/CardEdit").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查找所有card信息
     *
     * @param req
     * @param resp
     */
    private void queryAllCard(HttpServletRequest req, HttpServletResponse resp) {
        CardService service = new CardServiceImpl();
        List<Card> cards = service.queryAllCard();
        req.setAttribute("cards", cards);
        try {
            req.getRequestDispatcher("/Admin/CardMsg.jsp").forward(req, resp);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 添加IC卡信息
     *
     * @param req
     * @param resp
     */
    private void addCard(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");

        Map<String, String[]> map = req.getParameterMap();
        Card card = new Card();
        try {
            BeanUtils.populate(card, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //补充IC卡信息
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String cart_id = dateFormat.format(new Date());
        card.setCard_id(cart_id);


        CardService service = new CardServiceImpl();
        boolean b = service.addCard(card);
        ResultData rs = null;
        if (b) {
            //添加成功
            rs = new ResultData(400, "添加成功");
        } else {
            //添加失败
            rs = new ResultData(401, "添加失败");
        }
        String s = JSON.toJSONString(rs);
        try {
            //回写数据
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查找所有车位信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void findSeat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        SeatService service = new SeatServiceImpl();
        List<Seat> seats = service.queryAllSeat();
        String s = JSON.toJSONString(seats);
        resp.getWriter().write(s);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}

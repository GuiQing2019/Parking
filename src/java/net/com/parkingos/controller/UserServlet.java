package net.com.parkingos.controller;

import com.alibaba.fastjson.JSON;
import net.com.parkingos.bean.Contant;
import net.com.parkingos.bean.ResultData;
import net.com.parkingos.bean.Role;
import net.com.parkingos.bean.User;
import net.com.parkingos.service.UserService;
import net.com.parkingos.service.UserServiceImpl;
import net.com.parkingos.utils.PageUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @date 2020/4/7
 * 所有的user模块下面的业务都放到这个servlet中
 * * 通过method 判断不同的业务
 */

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method != null && method.length() > 0) {
            if (method.equals("addRole")) {
                //添加角色属性
                addRole(req, resp);
            }
            if (method.equals("queryAll")) {
                //查询角色所有属性
                queryAll(req, resp);
            }
            if (method.equals("delRole")) {
                //删除特定角色
                delRole(req, resp);
            }
            if (method.equals("findPageUser")) {
                //分页(最后一页存在失效,以及背景问题)
                findPageUser(req, resp);
            }
            if (method.equals("addUser")) {
                //添加用户
                addUser(req, resp);
            }
            if (method.equals("queryAllUser")) {
                //查询所有用户信息
                queryAllUser(req, resp);
            }
            if (method.equals("delUser")) {
                //删除特定用户
                delUser(req, resp);
            }
            if (method.equals("findRole")) {
                //查找角色
                findRole(req, resp);
            }
            if (method.equals("updateRole")) {
                //更新角色信息
                updateRole(req, resp);
            }
            if (method.equals("updateUser")) {
                //更新用户信息
                updateUser(req,resp);
            }
            if (method.equals("findUser")){
                //查找用户
                findUser(req,resp);
            }

        }


    }

    private void findUser(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        //查找用户
        String id = req.getParameter("id");
        //调用dao层
        User user = service.findUser(id);

        //回传数据
        if (user!=null){
            //请求转发
            req.setAttribute("user",user);
            try {
                req.getRequestDispatcher("/Admin/UserEdit.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                resp.getWriter().write("查询失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        //更新用户信息
        Map<String, String[]> map = req.getParameterMap();
        User user=new User();
        try {
            //将数据转化成User对象
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //创建回写数据集
        ResultData rs=null;
        boolean result=false;
        //获取数据
        result= service.updateUser(user);
        if (result){
            //更新成功
            rs=new ResultData(400,"更新成功");
        }else {
            //更新失败
            rs=new ResultData(401,"更新失败");
        }
        //转成Json回写数据
        String s = JSON.toJSONString(rs);
        //回写
        try {
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) {
        //更新角色信息
        Map<String, String[]> map = req.getParameterMap();
        Role role = new Role();
        ResultData rs = null;
        resp.setContentType("text/html;charset=utf-8");
        try {//将map转化成role类型
            BeanUtils.populate(role, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean result = service.updateRole(role);
        if (result) {
            //添加成功
            rs = new ResultData(400, "修改成功");
        } else {
            //失败
            rs = new ResultData(401, "修改失败");
        }
        String s = JSON.toJSONString(rs);
        try {
            //回些数据给前台
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findRole(HttpServletRequest req, HttpServletResponse resp) {
        //查找角色
        String id = req.getParameter("id");
        Role role = service.findRole(id);
        if (role != null) {
            req.setAttribute("role", role);
            try {
                //转发共享数据
                req.getRequestDispatcher("/Admin/RoleEdit.jsp").forward(req, resp);
                System.out.println("跳转完成!");
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void delUser(HttpServletRequest req, HttpServletResponse resp) {
        //删除特定用户
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        boolean result = service.delUser(id);
        ResultData rs = null;
        //回写数据
        if (result) {
            rs = new ResultData(400, "删除成功");
        } else {
            //添加失败
            rs = new ResultData(401, "删除失败");
        }

        //转成Json数据发送至前端
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


    private void queryAllUser(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        //获取数据
        List<User> users = service.queryAllUser();

        if (users != null) {

            try {
                req.setAttribute("users", users);
                req.getRequestDispatcher("/Admin/UserMsg.jsp").forward(req, resp);


            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) {
        //创建map对象存放页面数据 User对象进行转换
        resp.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        boolean result = false;
        ResultData rs = null;
        try {
            BeanUtils.populate(user, map);
            //调用服务层
            result = service.addUser(user);
            System.out.println(result);
            if (result) {
                rs = new ResultData(400, "添加成功!");

            } else {
                rs = new ResultData(401, "添加失败!");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //将数据转成json对象
        String s = JSON.toJSONString(rs);
        try {
            //回传数据
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 处理分页
     *
     * @param req
     * @param resp
     */
    private void findPageUser(HttpServletRequest req, HttpServletResponse resp) {
        //1.获取数据
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        //设置页面异常事件
        int currentPage = 0;
        try {
            currentPage = Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
            //如果有异常，设置默认值为1
            currentPage = 1;
        }


        //设置数据异常事件
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
            //如果有异常，设置默认值为10
            pageSize = 10;
        }
        PageUtils page = service.findPage(currentPage, pageSize);


        try {
            //3.处理，转发 页面显示
            if (page != null) {
                //转发
                //分享数据page
                req.setAttribute("page", page);
                req.getRequestDispatcher("/Admin/RoleMsg.jsp").forward(req, resp);
            } else {
                resp.getWriter().write("查询失败");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 删除Role中的角色
     *
     * @param req
     * @param resp
     */
    private void delRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        //获取RoleMsg中特定Id数据
        String id = req.getParameter("id");
        //调用服务层
        boolean result = service.delRole(id);

        ResultData rs = null;
        if (result) {
            //成功,回写数据
            rs = new ResultData(400, "删除成功");
        } else {
            //失败,回写数据
            rs = new ResultData(401, "删除失败");
        }

        //转成json字符串
        String s = JSON.toJSONString(rs);

        //回写数据
        resp.getWriter().write(s);

    }


    /**
     * 查询Role表中所有角色
     *
     * @param req
     * @param resp
     */
    private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        List<Role> role = service.queryAll();
        String s = JSON.toJSONString(role);
        resp.getWriter().write(s);


    }

    /**
     * 添加角色属性
     *
     * @param req
     * @param resp
     */
    public void addRole(HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("text/html;charset=utf-8");
        //通过界面获取数据
        String role_id = req.getParameter("role_id");
        String role_name = req.getParameter("role_name");

        System.out.println("名字");
        //调用服务层
        boolean result = service.addRole(role_id, role_name);

        ResultData rs = null;

        System.out.println(result);
        //回写数据
        if (result) {
            //添加成功.
            rs = new ResultData(400, "添加成功");

        } else {
            //添加失败
            rs = new ResultData(401, "添加失败");
        }

        //将对象转成字符串
        String s = JSON.toJSONString(rs);
        System.out.println(s + "回传中..");
        try {
            //回传数据
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package net.com.parkingos.controller;

import com.alibaba.fastjson.JSON;
import net.com.parkingos.bean.Contant;
import net.com.parkingos.bean.ResultData;
import net.com.parkingos.bean.User;
import net.com.parkingos.dao.UserDao;
import net.com.parkingos.service.UserService;
import net.com.parkingos.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ASUS
 * @date 2020/4/6
 * <p>
 * 登录模块
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        //获取页面数据
        String username = req.getParameter("user_id");
        String pwd = req.getParameter("user_pwd");
        User user = new User();
        //通过服务层业务员查找
        UserService service = new UserServiceImpl();
        user = service.Login(username, pwd);
        ResultData rs = null;
        String jsonstr = "";

        if (user != null) {
            //查询数据准确
            rs = new ResultData(Contant.LOGIN_SUCCESS, Contant.LOGIN_SUCCESS_MSG);
            jsonstr = JSON.toJSONString(rs);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } else {
            //查询失败
            rs = new ResultData(Contant.LOGIN_ERROR, Contant.LOGIN_ERROR_MSG);
            jsonstr = JSON.toJSONString(rs);
        }
        //将数据回传给Login.jsp
        resp.getWriter().write(jsonstr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package net.com.parkingos.test;

import net.com.parkingos.bean.Role;
import net.com.parkingos.bean.User;
import net.com.parkingos.dao.UserDao;
import net.com.parkingos.dao.UserDaoImpl;
import net.com.parkingos.service.UserService;
import net.com.parkingos.service.UserServiceImpl;
import net.com.parkingos.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/6
 */
public class Test01 {
    UserDao dao1 = new UserDaoImpl();

    @Test
    public void testUtils() {
        Connection con = JdbcUtils.getcon();
        System.out.println(con);
    }

    @Test
    public void testcon() {
        UserDao dao = new UserDaoImpl();

    }

    @Test
    public void testadd() {
        UserDao dao = new UserDaoImpl();
        for (int i = 0; i < 50; i++) {
            boolean result = dao.addRole("roo3" + i, "普通用户");
            System.out.println(result);
        }

    }

    @Test
    public void testquerryAll() {
        UserDao dao = new UserDaoImpl();
        List<Role> roles = dao.queryAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testdel() {
        boolean result = dao1.delRole("roo30");
        System.out.println(result);
    }

    @Test
    public void testPage() {
        List<Role> pageUser = dao1.findPageUser(1, 5);

        for (Role role : pageUser) {
            System.out.println(role);
        }
    }

    @Test
    public void testaddUser(){
        User user=new User("15478","r789","sadnw","sdwdw","15874","154111");
        UserService service=new UserServiceImpl();
        boolean b = service.addUser(user);
        System.out.println(b);
    }

    @Test
    public void testUserquery(){
        List<User> users = dao1.queryAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testfindRole(){
        Role r001 = dao1.findRole("r001");
        System.out.println(r001);
    }

    @Test
    public void testupdate(){
        Role role=new Role("r001","超级管理员11");
        boolean result = dao1.updateRole(role);
        System.out.println(result);
    }

    @Test
    public void testfindUser(){
        User user = dao1.findUser("15477");
        user.setUser_name("网吧123");
        user.setReal_name("15887");
        boolean result = dao1.updateUser(user);
        System.out.println(result);

    }

    public void testupdateUser(){

    }
}

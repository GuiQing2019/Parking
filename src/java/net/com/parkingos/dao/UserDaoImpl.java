package net.com.parkingos.dao;

import net.com.parkingos.bean.Role;
import net.com.parkingos.bean.User;
import net.com.parkingos.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ASUS
 */
public class UserDaoImpl implements UserDao {
    /**
     * 登录模块
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public User Login(String name, String pwd) {
        Connection conn = JdbcUtils.getcon();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from user where user_name = ? and user_pwd = ?";
        Object[] parms = {name, pwd};
        BeanHandler<User> bh = new BeanHandler<User>(User.class);
        User user = null;
        try {
            user = qr.query(conn, sql, bh, parms);
            System.out.println(user);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeAll(conn);
        }
        return user;
    }

    /**
     * 添加角色属性模块
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public boolean addRole(String id, String name) {
        QueryRunner qr = new QueryRunner();
        Connection con = JdbcUtils.getcon();
        String sql = "insert into role(role_id,role_name) values(?,?)";
        Object[] parms = {id, name};
        try {
            int r = qr.update(con, sql, parms);

            if (r > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> queryAll() {
        QueryRunner qr = new QueryRunner();

        Connection con = JdbcUtils.getcon();

        String sql = "select * from role";

        BeanListHandler<Role> blr = new BeanListHandler<Role>(Role.class);

        try {
            List<Role> query = qr.query(con, sql, blr);

            return query;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据id删除特定角色
     *
     * @param id
     * @return
     */
    @Override
    public boolean delRole(String id) {
        QueryRunner qr = new QueryRunner();

        Connection con = JdbcUtils.getcon();

        String sql = "delete from role where role_id = ?";

        Object[] params = {id};

        boolean r = false;
        try {
            int result = qr.update(con, sql, params);
            if (result > 0) {
                r = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeAll(con);
        }
        return r;
    }

    @Override
    public List<Role> findPageUser(int start, int num) {
        QueryRunner qr = new QueryRunner();
        Connection conn = null;
        List<Role> roleList = null;
        try {
            //获取数据库连接
            conn = JdbcUtils.getcon();
            String sql = "select * from role limit ?,?";
            Object[] params = {start, num};
            roleList = qr.query(conn, sql, new BeanListHandler<Role>(Role.class), params);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeAll(conn);
        }
        return roleList;
    }

    /**
     * 用户模块
     * 添加用户数据到表中
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        QueryRunner qr = new QueryRunner();
        Connection con = JdbcUtils.getcon();
        String sql = "insert into user(user_id,role_id,user_name,real_name,user_pwd,user_phone) values(?,?,?,?,?,?)";
        Object[] parmas = {user.getUser_id(), user.getRole_id(), user.getUser_name(), user.getReal_name(), user.getUser_pwd(), user.getUser_phone()};
        try {
            int update = qr.update(con, sql, parmas);
            if (update > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> queryAllUser() {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from user";
        BeanListHandler<User> bhl = new BeanListHandler<User>(User.class);
        Connection conn = JdbcUtils.getcon();
        try {

            List<User> users = qr.query(conn, sql, bhl);
            return users;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean delUser(String id) {
        QueryRunner qr = new QueryRunner();
        Connection con = JdbcUtils.getcon();

        String sql = "delete from user where user_id = ?";

        Object[] params = {id};

        boolean r = false;
        try {
            int result = qr.update(con, sql, params);
            if (result > 0) {
                r = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeAll(con);
        }
        return r;
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @Override
    public Role findRole(String id) {
        QueryRunner qr = new QueryRunner();
        Connection con = JdbcUtils.getcon();
        String sql = "select * from role where role_id =?";
        Object[] params = {id};
        BeanHandler<Role> role = new BeanHandler<Role>(Role.class);
        try {
            Role role1 = qr.query(con, sql, params, role);
            return role1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    @Override
    public boolean updateRole(Role role) {
        QueryRunner qr = new QueryRunner();
        Connection con = JdbcUtils.getcon();
        String sql = "update role set role_name=? where role_id =?";
        Object[] params = {role.getRole_name(), role.getRole_id()};

        try {
            int result = qr.update(con, sql, params);
            if (result > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查找用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User findUser(String id) {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from user where user_id =?";
        Connection conn = JdbcUtils.getcon();
        Object[] parmas = {id};
        BeanHandler<User> bh = new BeanHandler<User>(User.class);

        try {
            User user = qr.query(conn, sql, bh, parmas);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {

        QueryRunner qr = new QueryRunner();
        String sql = "update user set role_id = ?,user_name = ?,real_name = ?,user_pwd = ?, user_phone = ? where user_id=?";
        Connection con = JdbcUtils.getcon();
        Object[] paramas = {user.getRole_id(), user.getUser_name(), user.getReal_name(), user.getUser_pwd(), user.getUser_phone(), user.getUser_id()};

        try {
            int result = qr.update(con, sql, paramas);
            //回写数据
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

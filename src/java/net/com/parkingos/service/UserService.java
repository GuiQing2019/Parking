package net.com.parkingos.service;

import net.com.parkingos.bean.Role;
import net.com.parkingos.bean.User;
import net.com.parkingos.dao.UserDao;
import net.com.parkingos.dao.UserDaoImpl;
import net.com.parkingos.utils.PageUtils;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/6
 */
public interface UserService {


    /**
     * 登录业务
     *
     * @param name
     * @param pwd
     * @return
     */
    User Login(String name, String pwd);

    /**
     * 添加角色属性
     *
     * @param name
     * @param pwd
     * @return
     */
    boolean addRole(String name, String pwd);


    /**
     * 查找所有Role
     *
     * @return
     */
    List<Role> queryAll();


    /**
     * 根据特定的id删除Role
     *
     * @param id
     * @return
     */
    boolean delRole(String id);


    /**
     * 分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageUtils findPage(int currentPage, int pageSize);


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> queryAllUser();


    /** 删除用户
     * @param id
     * @return
     */
    boolean delUser(String id);

    /** 查找角色
     * @param id
     * @return
     */
    Role findRole(String id);

    /** 更新角色信息
     * @param role
     * @return
     */
    boolean updateRole(Role role);

    /** 查找用户
     * @param id
     * @return
     */
    User findUser(String id);

    /** 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);
}

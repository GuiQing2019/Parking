package net.com.parkingos.dao;

import net.com.parkingos.bean.Role;
import net.com.parkingos.bean.User;


import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/6
 */
public interface UserDao {

    User Login(String name, String pwd);

    boolean addRole(String name,String pwd);

    List<Role> queryAll();

    boolean delRole(String id);

    List<Role> findPageUser(int start,int num);

    boolean addUser(User user);

    List<User> queryAllUser();

    boolean delUser(String id);

    Role findRole(String id);

    boolean updateRole(Role role);

    User findUser(String id);

    boolean updateUser(User user);

}

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
public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public User Login(String name, String pwd) {
        return userDao.Login(name,pwd);
    }

    @Override
    public boolean addRole(String name, String pwd) {
        return userDao.addRole(name,pwd);
    }

    @Override
    public List<Role> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public boolean delRole(String id) {
        return userDao.delRole(id);
    }

    @Override
    public PageUtils findPage(int currentPage, int pageSize) {
        //数据Role的总数
        int totalCount = queryAll().size();

        //导航条个数和百度一样 10
        int navCount = 10;

        //组装pageUtils
        PageUtils pageUtils=new PageUtils(currentPage,pageSize,totalCount,navCount);

        /*
        * pageUtils 中没有数据
        * 需要通过查询数据添加
        * */

        int start=pageUtils.getStartIndex();

        //分页查询的数据
        List<Role> list = userDao.findPageUser(start, pageSize);

        //将数据都存入pageUtils中
        pageUtils.setPageData(list);

        return pageUtils;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public boolean delUser(String id) {
        return userDao.delUser(id);
    }

    @Override
    public Role findRole(String id) {
        return userDao.findRole(id);
    }

    @Override
    public boolean updateRole(Role role) {
        return userDao.updateRole(role);
    }

    @Override
    public User findUser(String id) {
        return userDao.findUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }


}

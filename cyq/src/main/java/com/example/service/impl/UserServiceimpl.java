package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.*;
import com.example.service.UserService;

import java.util.List;

public class UserServiceimpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 查询普通用户所有的信息
     */
    @Override
    public List<username> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    /**
     * 查询普通用户的昵称
     */
    @Override
    public List<nicheng> findName(String username) {
        return dao.findName(username);
    }

    /**
     * 查询普通用户登录账号密码是否正确
     */

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 查询管理员登录是否正确
     */

    @Override
    public Admin admin(Admin admin) {
        return dao.findadminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
    }

    /**
     * 添加新的普通用户
     */

    @Override
    public void addUser(User user) {
        dao.add(user);

    }

    /**
     * 查询普通用户是否已存在
     */
    @Override
    public List<ABC> findcopy() {
        return dao.findcopy();
    }

    /**
     * 查询聊天内容
     */
    @Override
    public void talkabout(username talk) {
        dao.talkabout(talk);
    }

    /**
     * 查询聊天内容id最大的一条记录
     */
    @Override
    public List<username> findmaxid() {
        return dao.findmaxid();
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAllUser() {
        //调用dao完成查询
        return dao.findAllUser();
    }

    @Override
    public void deleteUser(String username) {
        dao.delete(username);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public User findUsername(String username) {
        return dao.findUsername(username);
    }

    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if (user != null) {
            dao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }
}

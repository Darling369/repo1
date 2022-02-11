package com.example.dao;

import com.example.domain.*;

import java.util.List;

public interface UserDao {

    /**
     * 操作
     * @return
     */
    public List<username> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    Admin findadminByUsernameAndPassword(String username, String password);

    void add(User user);

    List<nicheng> findName(String username);

    public List<ABC> findcopy();

    void talkabout(username talk);

    List<username> findmaxid();

    List<User> findAllUser();

    void delete(String username);

    void updateUser(User user);

    User findUsername(String username);

    User findByCode(String code);

    void updateStatus(User user);
}

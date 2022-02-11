package com.example.service;


import com.example.domain.*;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<username> findAll();

    List<nicheng> findName(String username);

    User login(User user);

    Admin admin(Admin admin);



    void addUser(User user);

    public List<ABC> findcopy();

    void talkabout(username talk);

    List<username> findmaxid();


    List<User> findAllUser();

    void deleteUser(String username);

    void updateUser(User user);

    User findUsername(String username);

    boolean active(String code)  ;
}

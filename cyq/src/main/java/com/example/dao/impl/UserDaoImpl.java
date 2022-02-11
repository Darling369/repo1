package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.*;
import com.example.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Darling
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<username> findAll() {
        //查询聊天记录
        String sql = "select * from talk ";
        List<username> users = template.query(sql, new BeanPropertyRowMapper<username>(username.class));
        //使用jdbc操作数据库
        return users;
    }

    @Override
    public List<ABC> findcopy() {
        // 查询用户名是否已存在
        String sql = "select username from user ";
        List<ABC> users = template.query(sql, new BeanPropertyRowMapper<ABC>(ABC.class));
        return users;
    }



    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        //查询普通用户用户名密码是否正确
        try {
            String sql = "select Status from user where username=? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
//            String status = user.getStatus();
//            if(status.equals("Y")){
//                return user;
//            }else {
//                return null;
//            }
        } catch (Exception a) {
            a.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin findadminByUsernameAndPassword(String username, String password) {
        //查询管理员用户用户名密码是否正确
        try {
            System.out.println(username+password);
            String sql = "select username from admin where username=? and password =?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), username, password);
            return admin;
        } catch (Exception a) {
            a.printStackTrace();
            System.out.println("没找到");
            return null;
        }
    }

    @Override
    public void add(User user) {
        //注册新用户
        String sql = "insert into user value(?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),user.getStatus(),user.getCode());
    }


    @Override
    public void talkabout(username talk) {
        //插入聊天记录
        String sql = "insert into talk value(?,?,?,?,?)";
        template.update(sql, talk.getId(), talk.getUsername(), talk.getTalk(), talk.getName(), talk.getDate());
    }

    @Override
    public List<nicheng> findName(String username) {
        //查询用户昵称
        String sql = "select name from user where username = ?";
        List<nicheng> users = template.query(sql, new BeanPropertyRowMapper<nicheng>(nicheng.class), username);
        //使用jdbc操作数据库
        return users;
    }

    @Override
    public List<username> findmaxid() {
        //数据库id最大的一条数据
        String sql = "select * from talk order by id desc limit 1";
        List<username> users = template.query(sql, new BeanPropertyRowMapper<username>(username.class));
        //使用jdbc操作数据库
        return users;
    }

    @Override
    public List<User> findAllUser() {
        //查询用户信息
        String sql = "select * from user ";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        //使用jdbc操作数据库
        return users;
    }

    @Override
    public void delete(String username) {
        //管理员删除用户
        String sql = "delete  from user where username=? ";
        template.update(sql,username);

    }

    @Override
    public void updateUser(User user) {
        //管理员修改用户
        String sql = "update user set username = ?,password = ?,name = ?, email = ? where username = ?";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getUsername());
    }

    @Override
    public User findUsername(String username) {
        //回写修改用户页面
        String sql = "select * from user where username = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("queryForObject出现异常");
        }
        return user;

    }

    @Override
    public void updateStatus(User user) {
        String sql = "update user set status = 'Y' where username = ? ";
        template.update(sql,user.getUsername());
    }
}

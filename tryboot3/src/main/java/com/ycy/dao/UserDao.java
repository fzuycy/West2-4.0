package com.ycy.dao;

import com.ycy.pojo.User;

public interface UserDao {
    //根据用户名查询用户
    User getUserByName(String name);
    //插入一个用户
    int addUser(User user);
    //修改一个用户的信息
//    int updateUser(User user);
    //删除一个用户
//    int deleteUser(String name);
}

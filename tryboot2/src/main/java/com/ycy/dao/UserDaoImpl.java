package com.ycy.dao;

import com.ycy.pojo.User;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {
    //查询
    public User selectUserByName(String name){
        SqlSession session = MyBatisUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.getUserByName(name);
        System.out.println("我在查询"+user);
        return user;
    }

    //存入用户
    public void saveUser(User user){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.addUser(user);

        sqlSession.commit();

        sqlSession.close();
        System.out.println(user+"保存成功");
    }

//    //删除用户
//    public void deleteUser(User user){
//        SqlSession sqlSession = MyBatisUtils.getSession();
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        userDao.deleteUser(user.getUsername());
//        sqlSession.commit();
//
//        sqlSession.close();
//    }

}

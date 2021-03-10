package com.ycy;

import com.ycy.dao.UserDao;
import com.ycy.pojo.User;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void test1(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int user = userDao.addUser(new User("admin", "123456", "110", 0));

        sqlSession.commit();

        sqlSession.close();
        System.out.println(user+"保存成功");
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUserByName("admin");
        System.out.println(user);

        sqlSession.close();
    }


}

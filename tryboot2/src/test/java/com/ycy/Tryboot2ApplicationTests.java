package com.ycy;

import com.ycy.dao.UserDao;
import com.ycy.pojo.User;
import com.ycy.utils.LocalFileUtils;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tryboot2ApplicationTests {

    @Test
    public void getUserByNameTest(){
        SqlSession session = MyBatisUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.getUserByName("admin");
        System.out.println(user);
    }

    @Test
    public void addUserTest(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.addUser(new User("durant","2221","145672"));

        sqlSession.commit();

        sqlSession.close();
    }
    @Test
    public void updateUserTest(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.updateUser(new User("durant","44444","001"));

        sqlSession.commit();

        sqlSession.close();
    }
    @Test
    public void deleteUserTest(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.deleteUser("durant");
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void test1(){
        LocalFileUtils.CreateFolder("D:\\pictures\\hhh");

        LocalFileUtils.DeleteFile("D:\\pictures\\dog.jpg");
    }

}

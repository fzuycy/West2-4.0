package com.ycy;

import com.ycy.dao.ShowerDao;
import com.ycy.pojo.Shower;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

@SpringBootTest
class Tryboot3ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        Shower shower=new Shower(0,0,22,"file","ycy","D:\\pic\\ycy\\假文件夹3\\假文件夹4\\假图片6","假图片6","no",new Date(System.currentTimeMillis()));
        showerDao.addShower(shower);
        System.out.println(shower);
        session.commit();
        session.close();
    }
    @Test
    public void test2(){//查当前用户的所有信息
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        List<Shower> showerList = showerDao.getAllShowerList(6,(1-1)*4, 4);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();
    }

    @Test
    public void test3(){//查当前用户的所有信息
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        List<Shower> showerList = showerDao.getFileShowerList("file", (1 - 1) * 4, 4);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();
    }

    @Test
    public void test4(){//查当前用户的所有信息
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        Shower shower = showerDao.getShowerById(22);
        System.out.println(shower);
        session.close();
    }

}

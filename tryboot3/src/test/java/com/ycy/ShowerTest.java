package com.ycy;

import com.ycy.dao.ShowerDao;
import com.ycy.pojo.Shower;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ShowerTest {
    @Test
    public void test1(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int ycy = showerDao.getRootIdByUserName("ycy");
        System.out.println(ycy);

        session.close();
    }

    @Test
    public void test2(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int allCount = showerDao.queryForAllCount(15);
        System.out.println(allCount);

        session.close();
    }

    @Test
    public void test3(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.deleteShower(23);

        session.commit();
        session.close();
    }

    @Test
    public void test4(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.updateFileShower(8);

        session.commit();
        session.close();
    }

    @Test
    public  void  test5(){
        File file=new File("D:\\pic\\ycy\\假文件夹1");
        System.out.println(file.mkdirs());
    }
}

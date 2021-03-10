package com.ycy;

import com.ycy.dao.ShowerDao;
import com.ycy.dao.ShowerDaoImpl;
import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

public class ShowerDaoTest {
    @Test
    public void test1(){//查用户自己的内容
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        List<Shower> showerList = showerDao.getAllShowerList("ycy",(1-1)* Page.PAGE_SIZE, Page.PAGE_SIZE);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();
    }
    @Test
    public void test2(){//查管理员要的内容
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        List<Shower> showerList = showerDao.getCheckingFileShowerList("file",(1-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();
    }
    @Test
    public void test3(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.updateFileShower("file","1","yes");

        session.commit();

        session.close();

    }
    @Test
    public void test4(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int queryforfilecount = showerDao.queryForFileCount();
        System.out.println(queryforfilecount);
        session.close();
    }

    @Test
    public void test5(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int queryForAllCount = showerDao.queryForAllCount("ycy");
        System.out.println(queryForAllCount);
        session.close();
    }

    @Test
    public void test6(){

        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        int queryForCheckingFileCount = showerDao.queryForCheckingFileCount();
        System.out.println(queryForCheckingFileCount);
        session.close();
    }

    @Test
    public void  test7(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        Shower shower = showerDao.queryForFile("D:\\pictures\\ycy\\heihi.jpg");
        System.out.println(shower);
        session.close();

    }

    @Test
    public void test8(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        Shower shower = showerDao.queryForFile("D:\\pictures\\admin\\图片1");
        if(shower!=null)
        System.out.println(shower);
        else System.out.println("沙也没有");
        session.close();
    }


    @Test
    public void test9(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        Shower shower = new Shower(0, "file", "D:\\pictures\\ycy\\heihi1.jpg", new Date(System.currentTimeMillis()), "heihi.jpg", "ycy", "no");

//                showerDao.addShower(new Shower(0,"file","D:\\pictures\\ycy\\heihi.jpg",new Date(System.currentTimeMillis()),"heihi.jpg","ycy","no"));
showerDao.addShower(shower);
        System.out.println(shower);
        session.commit();
        session.close();
    }
    @Test
    public  void test10(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        Shower shower=new Shower(0,null,"1",null,"图片2","ycy",null);
        showerDao.deleteShower("1");
        session.commit();

        session.close();
    }

    @Test
    public  void test11(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        Shower shower=new Shower(0,null,null,null,"好看的云霞","ycy",null);
        Shower myShower = showerDao.getMyShower(shower);
        System.out.println(myShower);
        session.commit();

        session.close();
    }
}

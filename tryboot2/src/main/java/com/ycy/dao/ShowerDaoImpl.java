package com.ycy.dao;

import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public class ShowerDaoImpl {
    //begin=(当前页-1 ）*每页记录数
    //用户查看自己上传的图片信息
    public  List<Shower> getAllShowerList(String username,int begin,int pageSize){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        List<Shower> showerList = showerDao.getAllShowerList(username,begin, pageSize);

//        for (Shower shower:showerList){
//            System.out.println(shower);
//        }

        session.close();

        return showerList;
    }
    public  List<Shower> getFileShowerList(String mytype,int begin,int pageSize){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        List<Shower> showerList = showerDao.getFileShowerList(mytype,begin,pageSize);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();

        return showerList;
    }
//用于查找是否存在特定路径的图片
    public Shower queryForFile(String url){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        Shower shower = showerDao.queryForFile(url);
        System.out.println(shower);
        session.close();

        return  shower;
    }

    public  List<Shower> getCheckingFileShowerList(String mytype,int begin,int pageSize){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        List<Shower> showerList = showerDao.getCheckingFileShowerList(mytype,begin,pageSize);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();

        return showerList;
    }

    public  void updateFileShower(String url,String res){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.updateFileShower("file",url,res);

        session.commit();

        session.close();
    }

    public  int queryForFileCount(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int queryForFileCount = showerDao.queryForFileCount();
        System.out.println(queryForFileCount);
        session.close();

        return queryForFileCount;
    }

    public  int queryForAllCount(String username){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int queryForAllCount = showerDao.queryForAllCount(username);
        System.out.println(queryForAllCount);
        session.close();

        return  queryForAllCount;
    }
    //给管理员用的，查看未审核的信息条数
    public  int queryForCheckingFileCount(){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        int queryForCheckingFileCount = showerDao.queryForCheckingFileCount();
        System.out.println(queryForCheckingFileCount);
        session.close();
        return queryForCheckingFileCount;
    }

    public void addShower(Shower shower){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
//        Shower shower1 = new Shower(0, "file", "D:\\pictures\\ycy\\heihi.jpg", new Date(System.currentTimeMillis()), "heihi.jpg", "ycy", "no");
//      showerDao.addShower(new Shower(0,"file","D:\\pictures\\ycy\\heihi.jpg",new Date(System.currentTimeMillis()),"heihi.jpg","ycy","no"));
        showerDao.addShower(shower);
        System.out.println(shower);
        session.commit();
        session.close();
    }

    public void delShower(String url){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        showerDao.deleteShower(url);
        session.commit();

        session.close();
    }

    public Shower getMyShower(Shower shower){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        Shower myShower = showerDao.getMyShower(shower);

        System.out.println(myShower);

        session.close();
        return myShower;
    }
}

package com.ycy.dao;

import com.ycy.pojo.Shower;
import com.ycy.utils.MyBatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public class ShowerDaoImpl {
    //begin=(当前页-1 ）*每页记录数
    //用户查看自己上传的图片信息
    //初步改好了
    public  List<Shower> getAllShowerList(int ParentId,String username,int begin,int pageSize){ //初步改好了
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        //当前页-1 ）*每页记录数
        List<Shower> showerList = showerDao.getAllShowerList(ParentId,begin,pageSize);

        for (Shower shower:showerList){
            System.out.println(shower);
        }

        session.close();

        return showerList;
    }
//初步改好了
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

    public  void updateFileShower(int myId){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.updateFileShower(myId);

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
//通过父文件夹id计算旗下的所有文件及文件夹总数
    public  int queryForAllCount(int parentId){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int allCount = showerDao.queryForAllCount(parentId);
        System.out.println(allCount);

        session.close();
        return allCount;
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

//    改好了
    public void addShower(Shower shower){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        showerDao.addShower(shower);
        System.out.println(shower);
        session.commit();
        session.close();
    }

    public void delShower(int myId){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);

        showerDao.deleteShower(myId);
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

    //改好了
    public int getRootIdByUsername(String name){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        int id = showerDao.getRootIdByUserName(name);
        System.out.println(id);

        session.close();

        return id;
    }

    public Shower getShowerById(int id){
        SqlSession session = MyBatisUtils.getSession();
        ShowerDao showerDao = session.getMapper(ShowerDao.class);
        Shower shower = showerDao.getShowerById(id);
        System.out.println(shower);
        session.close();
        return shower;
    }
}

package com.ycy.dao;

import com.ycy.pojo.Shower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowerDao {
    //查当前用户所有的信息
    List<Shower> getAllShowerList(@Param("parentid")int parentid, @Param("begin") int begin, @Param("pageSize") int pageSize);
    //只查File表，而且是查全部信息
    List<Shower> getFileShowerList(@Param("mytype")String type ,@Param("begin") int begin,@Param("pageSize") int pageSize);

    List<Shower> getCheckingFileShowerList(@Param("mytype")String type ,@Param("begin") int begin,@Param("pageSize") int pageSize);

    //通过url更新图片的审核状态
    void updateFileShower(@Param("myid")int myId);

    //计算总记录条数(只查图片数)
    int queryForFileCount();

    //通过父id计算总记录条数(不只是图片)
    int queryForAllCount(@Param("parentid")int id);

    //查看所有自己的图片（包括未审核）
    int queryForCheckingFileCount();

    Shower queryForFile(@Param("url") String url);

    void addShower(Shower shower);

    void deleteShower(@Param("myid")int myId);

    Shower getMyShower(Shower shower);

    int getRootIdByUserName(@Param("sname") String sname);

    Shower getShowerById(@Param("id")int id);

}

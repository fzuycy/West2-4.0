package com.ycy.dao;

import com.ycy.pojo.Shower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowerDao {
    //联合查询（不查ischecked参数）查mytype,date, filename, username
    List<Shower> getAllShowerList(@Param("username")String name, @Param("begin") int begin, @Param("pageSize") int pageSize);
    //只查File表，而且是查全部信息
    List<Shower> getFileShowerList(@Param("mytype")String type ,@Param("begin") int begin,@Param("pageSize") int pageSize);

    List<Shower> getCheckingFileShowerList(@Param("mytype")String type ,@Param("begin") int begin,@Param("pageSize") int pageSize);

    //通过url更新图片的审核状态
    void updateFileShower(@Param("mytype")String mytype,@Param("url")String url,@Param("res")String res);

    //计算总记录条数(只查图片数)
    int queryForFileCount();
    //计算总记录条数(不只是图片)
    int queryForAllCount(@Param("username") String name);
    //查看所有自己的图片（包括未审核）
    int queryForCheckingFileCount();

    Shower queryForFile(@Param("url") String url);

    void addShower(Shower shower);

    void deleteShower(@Param("path")String path);

    Shower getMyShower(Shower shower);
}

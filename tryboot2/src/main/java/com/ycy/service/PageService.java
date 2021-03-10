package com.ycy.service;

import com.ycy.dao.ShowerDaoImpl;
import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.utils.LocalFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    @Autowired
    private ShowerDaoImpl showerDaoImpl;

    public Page getManagerPage(int pageNo){
        Page page=new Page();
//管理员页面的信息即未审核的图片信息
        int queryForFileCount = showerDaoImpl.queryForCheckingFileCount();//未审核图片的总记录数(未审核)
        page.setPageTotalCount(queryForFileCount);
        int pageTotal=queryForFileCount/Page.PAGE_SIZE;//获取总页数
        if(queryForFileCount%Page.PAGE_SIZE>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //数据边境的有效检查
        if(pageNo>pageTotal){//页面过大，默认处理为最后一页
            pageNo=pageTotal;
        }
        if(pageNo<1){//边境处理，跳转页面过小，默认处理为第一页
            pageNo=1;
        }

        System.out.println("当前页数为"+pageNo);
        page.setPageNo(pageNo);
        page.setPageSize(Page.PAGE_SIZE);


//计算得到数据库分页查询的表的查询起点
        int begin=(pageNo-1)*Page.PAGE_SIZE;
        //得到记录数据数组
        List<Shower> fileShowerList = showerDaoImpl.getCheckingFileShowerList("file", begin, Page.PAGE_SIZE);

        for (int i = 0; i < fileShowerList.size(); i++) {
            fileShowerList.get(i).setId(i);
        }
        page.setMyShower(fileShowerList);

        return page;
    }

    public Page getUserPage(String username,int pageNo) {
        Page page=new Page();
//用户界面的显示要求：能显示所有用户上传的文件信息，但只有通过审核的文件才能下载
        int queryForAllCount = showerDaoImpl.queryForAllCount(username);//该用户数据库中符合显示要求的总记录数
        page.setPageTotalCount(queryForAllCount);
        int pageTotal=queryForAllCount/Page.PAGE_SIZE;//得到总页数
        if(queryForAllCount%Page.PAGE_SIZE>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        if(pageNo<1){
            pageNo=1;
        }

        page.setPageSize(Page.PAGE_SIZE);


        page.setPageNo(pageNo);
//获取查询数据库表的起点begin
        int begin=(pageNo-1)*Page.PAGE_SIZE;//这里pageNo=0出错

        List<Shower> allShowerList = showerDaoImpl.getAllShowerList(username, begin, Page.PAGE_SIZE);

        for (int i = 0; i<allShowerList.size(); i++) {//这里是为了前端处理信息方便，应前端要求加了一个参数id，方便遍历
            allShowerList.get(i).setId(i);
        }
        page.setMyShower(allShowerList);

        return page;
    }

    public boolean delShower(Shower shower){
//实际上这边只需要让前端传
        Shower myShower = showerDaoImpl.getMyShower(shower);//通过前端回传的用户名和文件名找到对应路径
        System.out.println("检索的结果显示即将被删除的照片是"+myShower);
        if(myShower==null)//不存在
            return false;
        else{
            LocalFileUtils.DeleteFile(myShower.getPath());//根据对应路径删除图片文件
            showerDaoImpl.delShower(myShower.getPath());//删除图片在数据库中的记录信息
        }

        return true;
    }

    public boolean updateByCheckRes(Shower shower,boolean res){
        Shower myShower = showerDaoImpl.getMyShower(shower);
        if(myShower==null)//该图片不存在
            return false;
        if(res){//把审核状态更新为通过
            showerDaoImpl.updateFileShower(myShower.getPath(),"yes");
        }
        else {
            //这里先删除磁盘里的对应图片

            LocalFileUtils.DeleteFile(myShower.getPath());

            //再删除未通过审核的图片信息
            showerDaoImpl.delShower(myShower.getPath());

        }
        return true;
    }
}

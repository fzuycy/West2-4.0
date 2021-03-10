package com.ycy.service;

import com.ycy.dao.ShowerDaoImpl;
import com.ycy.dao.UserDaoImpl;
import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.utils.LocalFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PageService {
    @Autowired
    private ShowerDaoImpl showerDaoImpl;

    @Autowired
    private UserDaoImpl userDaoImpl;
//
    public Page getManagerPage(int pageNo){
        Page page=new Page();

        int queryForFileCount = showerDaoImpl.queryForCheckingFileCount();//总记录数(未审核)
        page.setPageTotalCount(queryForFileCount);
        int pageTotal=queryForFileCount/Page.PAGE_SIZE;
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
//这边可能有bug
//        if(pageNo==0)
//            pageNo=1;

        System.out.println("当前页数为"+pageNo);
        page.setPageNo(pageNo);
        page.setPageSize(Page.PAGE_SIZE);



        int begin=(pageNo-1)*Page.PAGE_SIZE;
        List<Shower> fileShowerList = showerDaoImpl.getCheckingFileShowerList("file", begin, Page.PAGE_SIZE);

        for (int i = 0; i < fileShowerList.size(); i++) {
            fileShowerList.get(i).setId(i);
        }
        page.setMyShower(fileShowerList);

        return page;
    }



//改好了
    public Page getUserPage(String username,int MyId,int pageNo) {//这里的MyId其实已经是所在当前文件夹的id了，但是又作为父文件夹查看其中的信息
        Page page=new Page();

        //处理parentId和MyId

        if(MyId==0){//记得在控制层将id默认设置为0，表示此时前端没给id，则默认视为访问根目录,即前端没给当前文件夹的id值时，需要通过用户名去找到根目录
            int rootFolderId = userDaoImpl.selectUserByName(username).getRootFolderId();//获取根目录的值
            page.setParentId(0);
            page.setMyId(rootFolderId);
        }
        else{//当前为根目录或者非根目录(parentId!=0)(根据前端给的id值找到对应的myId和parentId即可)

            Shower showerById = showerDaoImpl.getShowerById(MyId);
            page.setMyId(MyId);
            page.setParentId(showerById.getParentid());

        }

        int queryForAllCount = showerDaoImpl.queryForAllCount(page.getMyId());//总记录数(用户当前文件夹下所有记录总数)
        page.setPageTotalCount(queryForAllCount);
        int pageTotal=queryForAllCount/Page.PAGE_SIZE;
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


//        page.setPageNo(pageNo);
        page.setPageSize(Page.PAGE_SIZE);



//        if(pageNo==0)
//            pageNo=1;
        page.setPageNo(pageNo);

        int begin=(pageNo-1)*Page.PAGE_SIZE;//这里pageNo=0出错

        //改好，通过当前文件夹id查找相关子记录
        List<Shower> allShowerList = showerDaoImpl.getAllShowerList(page.getMyId(),username, begin, Page.PAGE_SIZE);

        for (int i = 0; i<allShowerList.size(); i++) {
            allShowerList.get(i).setId(i);
        }
        page.setMyShower(allShowerList);

        return page;
    }

////  用户删除信息的第一套方案
//    public void delShower(Shower shower){
//
//        Shower myShower = showerDaoImpl.getMyShower(shower);
//        showerDaoImpl.delShower(myShower.getPath());//删除数据库里的记录
//        //删除磁盘上的数据
//        //还没做
//    }
////

    //
    public boolean delShower(int myId){

        //删除磁盘上的数据(先删磁盘上的)
        Shower showerById = showerDaoImpl.getShowerById(myId);//通过编号找到特定的文件或文件夹对象
        //这里说明一下，以编号为查询键值，依据是我把该参数设置成自增的形式，能保证每条记录id的唯一性，这样就能有效快速地查询相应数据
        System.out.println("根据id可得"+showerById);
        if(showerById!=null){
            LocalFileUtils.DeleteFile(showerById.getPath());//先删磁盘里的
            showerDaoImpl.delShower(myId);// 再删除数据库里的记录
            return true;//因为如果先删数据库记录，那接下去就拿不到文件或文件夹的url了
        }
        else{
            System.out.println("不存在该文件，无法删除");
            return  false;
        }
    }
//第一套方案
//    public void updateByCheckRes(Shower shower,boolean res){
//        Shower myShower = showerDaoImpl.getMyShower(shower);
//        if(res){
//            showerDaoImpl.updateFileShower(myShower.getPath(),"yes");
//        }
//        else {
//            //这里先删除磁盘里的对应图片
//
//            LocalFileUtils.DeleteFile(myShower.getPath());
//
//            //再删除未通过审核的图片信息
//            showerDaoImpl.delShower(myShower.getPath());
//
//
//        }
//    }


    public void updateByCheckRes(int myId,boolean res){
        if(res){//若审核通过
            showerDaoImpl.updateFileShower(myId);//通过唯一id更新审核状态
        }
        else {//未通过审核
            //先利用唯一编号获取图片对象
            Shower showerById = showerDaoImpl.getShowerById(myId);
            //这里先删除磁盘里的对应图片
            LocalFileUtils.DeleteFile(showerById.getPath());

            //再删除未通过审核的图片信息
            showerDaoImpl.delShower(myId);


        }
    }

    public void NewFolder(int folderId,String name){
        Shower showerById = showerDaoImpl.getShowerById(folderId);
        LocalFileUtils.CreateFolder(showerById.getPath()+"\\"+name);//其实这里可以不用急着在磁盘上建文件夹，只要能拿到正确的路径，一个file.mkdirs()就能解决
        Shower shower=new Shower(0,0,folderId,"folder",showerById.getUsername(),showerById.getPath()+"\\"+name,name,null,new Date(System.currentTimeMillis()));
        showerDaoImpl.addShower(shower);
    }

}

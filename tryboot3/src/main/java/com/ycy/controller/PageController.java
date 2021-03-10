package com.ycy.controller;

import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @ResponseBody
     @PostMapping("/managershow")//这里默认页数是1 //返回未审核的图片信息
     public Page ManagerPage(@RequestParam(value = "pageNo",required = false,defaultValue = "1")Integer myPageNo){
//        value = "pageNo",required = false,defaultValue = "1"
        System.out.println("manger收到数据"+myPageNo);
         Page managerPage = pageService.getManagerPage(myPageNo);


         return managerPage;
     }
     @ResponseBody
     @PostMapping("/usershow")
    public Page UserPage(@RequestParam(value = "ParentId",required = false,defaultValue = "0")Integer parentId,@RequestParam( value = "pageNo",required = false,defaultValue = "1")Integer myPageNo,@RequestParam("username")String name)
     {
         System.out.println("user收到数据"+myPageNo);
         System.out.println("名字是"+name);
         System.out.println("父文件夹ID为 "+parentId);
         Page userPage = pageService.getUserPage(name, parentId,myPageNo);
         return  userPage;
     }

//用户删除信息的第一套方案
//     @ResponseBody
//    @GetMapping("/userDelFile")
//    public String deleteByUser(Shower shower){
////         @RequestParam("username")String myName,@RequestParam("filename")String myFileName
//
//        pageService.delShower(shower);
////记得还要删除磁盘上的数据
//        return "用户自主删除图片成功";
//     }

    @ResponseBody
    @GetMapping("/userDelFile")
    public boolean deleteByUser(@RequestParam("myId")int myid){//myId表示当前所要删除的文件或文件夹的编号

        return  pageService.delShower(myid);//删除成功则返回true，无法删除则返回false
        //记得还要删除磁盘上的数据
    }


//     @ResponseBody
//     @GetMapping("/managerUpd")
//     public String managerCheck(Shower shower,@RequestParam("res")boolean checkRes){
//
//        pageService.updateByCheckRes(shower,checkRes);
//        return "管理员审核结束";
//     }

    @ResponseBody
    @GetMapping("/managerUpd")//管理员对未审核图片信息进行检查
    public String managerCheck(@RequestParam("myId")int myId,@RequestParam("res")boolean checkRes){
//
        pageService.updateByCheckRes(myId,checkRes);
        return "管理员审核结束";
    }

    @ResponseBody
    @GetMapping("/cFolder")
    public String createFolder(@RequestParam("folderId")int id,@RequestParam("folderName")String name){//在当前文件夹(myid=folderId)下新建文件夹
        // folderName代表新建文件夹名(这里要注意的是，因为我在设计表时，是把记录编号作为唯一标识的，因此这里即使出现同名同级的文件夹也不奇怪，当然也可以在一条路径上连续出现同名文件夹)
        //这一点是我之前最开始做时所没法做到的，可以说数据库表的设计真的很重要
        pageService.NewFolder(id,name);
        return "新建文件夹成功";
    }
}

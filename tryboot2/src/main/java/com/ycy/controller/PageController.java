package com.ycy.controller;

import com.ycy.pojo.Page;
import com.ycy.pojo.Shower;
import com.ycy.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    //管理员界面
    @ResponseBody
     @PostMapping("/managershow")//这里默认页数是1 //返回未审核的图片信息
     public Page ManagerPage(@RequestParam(value = "pageNo",required = false,defaultValue = "1")Integer myPageNo){
        //这里将当前页面页码的默认值设为1
        System.out.println("manger访问的页数为 "+myPageNo);
         Page managerPage = pageService.getManagerPage(myPageNo);//得到对应的Page对象（里面存有当前页的页面记录数据）

         return managerPage;
     }
     //用户界面
     @ResponseBody
     @PostMapping("/usershow")
    public Page UserPage(@RequestParam( value = "pageNo",required = false,defaultValue = "1")Integer myPageNo,@RequestParam("username")String name)
     {
         //将当前页码默认值设为1
         System.out.println("用户的访问页数为 "+myPageNo);
         System.out.println("名字是"+name);
         Page userPage = pageService.getUserPage(name, myPageNo);//根据页码和用户名获取相应的用户界面信息
         System.out.println(userPage);
         return  userPage;
     }

     @ResponseBody
    @PostMapping("/userDelFile")
    public boolean deleteByUser(@RequestParam("username")String name,@RequestParam("sname")String filename){
        //这里前端只需传用户名和文件名，其他参数不需要，后端这边采用直接对象映射来取值
         Shower shower=new Shower();
         shower.setSname(filename);
         shower.setUsername(name);
         System.out.println("用户删除图片");
         System.out.println("接收到要删除的图片是"+shower);
        return pageService.delShower(shower);
     }

     @ResponseBody
     @PostMapping("/managerUpd")
     public boolean managerCheck(Shower shower,@RequestParam("res")boolean checkRes){

         System.out.println("管理员审核开始"+shower+" "+checkRes);
        return  pageService.updateByCheckRes(shower,checkRes);
     }
}

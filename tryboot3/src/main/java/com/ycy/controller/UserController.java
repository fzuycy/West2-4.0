package com.ycy.controller;

import com.ycy.pojo.User;
import com.ycy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/userlogin")//用户登录
    public String UserLogin(User user1, HttpServletRequest request){//最好封装一个对象给他,还是说让前端那边接收到2或1时顺便把用户信息存了？
        System.out.println("登陆时的session是"+request.getSession());//取得session,这个是之前测试用的，就懒得删了
        System.out.println("我的user"+user1);//打印一下信息
        System.out.println("我在登陆");
        String res="";
        res+=userService.CheckLogin(user1);
        return res;//有2,1,0，-1 四种返回值
    }

    @ResponseBody
    @PostMapping("/register")//用户注册
    public Boolean UserRegister(@RequestParam("username")String name,@RequestParam("userpsw")String psw,@RequestParam("usertel")String tel,HttpServletRequest request){
        System.out.println("注册时的session是"+request.getSession());
        //打印一下消息，之前一直收不到前端传过来的请求参数，现在算是好了
        User user=new User(name,psw,tel,0);
        System.out.println(name+" "+psw+" "+tel);
        return userService.CheckRegister(user);
    }

}

package com.ycy.controller;

import com.ycy.pojo.User;
import com.ycy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = {"/","/login"})
//    public String loginPage(){
//        userService.myPrint();
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String checkLogin(User user, HttpSession httpSession){
//        System.out.println(user);
//        if(StringUtils.hasLength(user.getUsername())&&StringUtils.hasLength(user.getUserpsw())&&StringUtils.hasLength(user.getUsertel())){
//
//            System.out.println("已登录！");
//            httpSession.setAttribute("loginUser",user);
//            if(user.getUsername().equals("admin")){
//                System.out.println("欢迎管理员！");
//                httpSession.setAttribute("isManager",true);
//            }
//            else httpSession.setAttribute("isManager",true);
//            return "redirect:/jump";
//        }
//        else{
//            System.out.println("未成功登录！");
//            return "login";
//        }
//    }
//
//    @GetMapping("/jump")
//    public String gotoMainPage(){
//        return "mainPage";
//    }

    //用户登录
    @ResponseBody
    @PostMapping("/userlogin")
    public String UserLogin(User user1, HttpServletRequest request){//最好封装一个对象给他,还是说让前端那边接收到2或1时顺便把用户信息存了？
        System.out.println("登陆时的session是"+request.getSession());
        System.out.println("我的user"+user1);
        System.out.println("我在登陆");
        String res="";
        res+=userService.CheckLogin(user1);
        return res;
    }

    //用户注册
    @ResponseBody
    @PostMapping("/register")
    public Boolean UserRegister(@RequestParam("username")String name,@RequestParam("userpsw")String psw,@RequestParam("usertel")String tel,HttpServletRequest request){
        System.out.println("注册时的session是"+request.getSession());
        User user=new User(name,psw,tel);
        System.out.println(name+" "+psw+" "+tel);
        return userService.CheckRegister(user);//注册成功返回true，否则为false
    }

//    @ResponseBody
//    @PostMapping("/userdestory")
//    public  Boolean UserDestory(User user){
//        return  userService.CheckDestory(user);
//    }
}

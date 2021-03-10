package com.ycy.service;

import com.ycy.dao.UserDaoImpl;
import com.ycy.pojo.Shower;
import com.ycy.pojo.User;
import com.ycy.utils.LocalFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    public void myPrint(){
        System.out.println("我在业务层");
    }

    public int CheckLogin(User user){
        User Duser=userDaoImpl.selectUserByName(user.getUsername());
        if(Duser!=null){
            if(Duser.getUserpsw().equals(user.getUserpsw()))
            {
                if(user.getUsername().equals("admin")) return 2;//管理员登录
                return 1;//名对码对
            }
            else return 0;//名对码错
        }
        else return -1;//名错（用户不存在）
    }

    public boolean CheckRegister(User user)
    {
        User user1 = userDaoImpl.selectUserByName(user.getUsername());
        if(user1!=null){
            return false;//用户已存在
        }
        else{
            //这边还要写一个把用户存入数据库的操作
            userDaoImpl.saveUser(user);
            //还要在磁盘上创一个真实文件夹(同名文件夹)

            LocalFileUtils.CreateFolder("D:\\pictures\\"+user.getUsername());

            return true;//用户不存在，已经成功注册账户
        }

    }
//    public boolean CheckDestory(User user){
//        User Duser = userDaoImpl.selectUserByName(user.getUsername());
//        if (Duser!=null){//记得检查非空
//            if(Duser.getUsername().equals(user.getUsername())){
//                if(Duser.getUserpsw().equals(user.getUserpsw())){
//                    userDaoImpl.deleteUser(user);
//                    return true;
//                }
//                else return false;
//            }
//            else return false;
//        }
//        else return false;
//    }


}

package com.ycy.service;

import com.ycy.dao.ShowerDaoImpl;
import com.ycy.dao.UserDaoImpl;
import com.ycy.pojo.Shower;
import com.ycy.pojo.User;
import com.ycy.utils.LocalFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private ShowerDaoImpl showerDaoImpl;

    public void myPrint(){
        System.out.println("我在业务层");
    }

    public int CheckLogin(User user){//检查用户登录的信息，这里是用Dao查了一下传过来的参数封装成的对象是否存在于数据库中
        User Duser=userDaoImpl.selectUserByName(user.getUsername());
        if(Duser!=null){//不为空，说明该用户存在
            if(Duser.getUserpsw().equals(user.getUserpsw()))
            {//比对账户，简单地区分一下普通用户和管理员
                if(user.getUsername().equals("admin")) return 2;//管理员登录
                return 1;//名对码对
            }
            else return 0;//名对码错
        }
        else return -1;//名错（用户不存在）
    }

    public boolean CheckRegister(User user)//检查注册的信息
    {
        User user1 = userDaoImpl.selectUserByName(user.getUsername());
        if(user1!=null){
            return false;//用户已存在
        }
        else{

            //先新建一个用户根文件夹（逻辑上）
            //这里我的想法是，每个用户注册时都会为其自动分配一个根文件夹，之后该用户的所有存取都在这个文件夹里进行
            showerDaoImpl.addShower(new Shower(0,0,0,"folder",user.getUsername(),"D:\\pic\\"+user.getUsername(),user.getUsername(),null,new Date(System.currentTimeMillis())));
            //再将根目录的特定序号取出，赋给User
            //这里我采用将特殊的Shower类对象（根文件夹）的编号ID与对应的用户绑定在一起
            user.setRootFolderId(showerDaoImpl.getRootIdByUsername(user.getUsername()));
            //这边还要写一个把用户的根文件夹存入数据库的操作
            userDaoImpl.saveUser(user);

            //这里不用急着去磁盘上新建文件夹，只要能保存好路径，并且在需要的时候获取到正确的路径，简简单单地通过file.mkdirs()即可创建好对应的文件夹，非常方便
            //保存好路径，在上传文件，存储文件时顺便新建磁盘上真是文件夹

            return true;//用户不存在，已经成功注册账户
        }

    }

}

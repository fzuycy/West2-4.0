package com.ycy.service;

import com.ycy.dao.ShowerDaoImpl;
import com.ycy.pojo.Shower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

@Service
public class FileService {

    @Autowired
    private  ShowerDaoImpl showerDao;

//    public boolean upLoadFile(String username, String filename, MultipartFile myfile) throws IOException {
//            //获取源文件后缀名
//            String originalFilename = myfile.getOriginalFilename();
//            int lastIndexOf=originalFilename.lastIndexOf(".");
//            String substring = originalFilename.substring(lastIndexOf);
//            System.out.println("文件名后缀为"+substring);
//            //保存到文件服务器或OSS服务器
//            String url="";
//            if(StringUtils.hasLength(filename))
//                url="D:\\pictures\\"+username+"\\"+filename+substring;
//            else
//            {
//                filename=originalFilename;
//                url="D:\\pictures\\"+username+"\\"+originalFilename;
//            }
//            if(showerDao.queryForFile(url)!=null){
//                System.out.println("该文件名已存在，请改名");
//                return false;
//            }
////                myfile.transferTo(new File("D:\\pictures\\"+originalFilename));
//            else {
//                //保存到磁盘中
//                myfile.transferTo(new File(url));
//                //同时保存到数据库中
//
//                //这个地方修改过，有bug
//                Shower shower=new Shower( 0,0,0,"file",username,url,filename,"no",new Date(System.currentTimeMillis()));
//                showerDao.addShower(shower);
//                System.out.println("文件上传成功");
//                return true;
//            }
//    }
    //第二方案
//    public void upLoadFile(int folderId,MultipartFile myfile) throws IOException {//此处的id是父id
//            //获取源文件后缀名
//            String originalFilename = myfile.getOriginalFilename();
//            int lastIndexOf=originalFilename.lastIndexOf(".");
//            String substring = originalFilename.substring(lastIndexOf);
//            System.out.println("文件名后缀为"+substring);
//            //保存到文件服务器或OSS服务器
//            Shower showerById = showerDao.getShowerById(folderId);
//            String url=showerById.getPath();//由当前文件夹路径+图片名得到完整路径
//            System.out.println("文件夹路径为 "+url);
//            File file=new File(url);
//            file.mkdirs();
//            url=url+"\\"+originalFilename;
//        //先保存到磁盘中
//         myfile.transferTo(new File(url));
//         Shower shower=new Shower(0,0,folderId,"file",showerById.getUsername(),url,originalFilename,"no",new Date(System.currentTimeMillis()));
//         //再保存到数据库中
//         showerDao.addShower(shower);
//
//    }


    public boolean upLoadFile(int folderId,MultipartFile myfile) throws IOException {//此处的id是父id
        //获取源文件后缀名
        String originalFilename = myfile.getOriginalFilename();
        int lastIndexOf=originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(lastIndexOf);
        System.out.println("文件名后缀为"+substring);
        if(!substring.equals(".jpg")&&!substring.equals("jpeg"))
            return false;
        //保存到文件服务器或OSS服务器
        Shower showerById = showerDao.getShowerById(folderId);//通过编号获取所在文件夹对应的shower对象
        String url=showerById.getPath();//当前文件夹的路径
        System.out.println("文件夹路径为 "+url);
        File file=new File(url);
        file.mkdirs();//真正在磁盘上创建存放上传文件的文件夹
        url=url+"\\"+originalFilename;//由当前文件夹路径+图片名得到完整路径
        //先保存到磁盘中
        myfile.transferTo(new File(url));//把上传文件存到正确的文件夹中
        //同时以folderId作为父文件夹，创建一个新的文件夹类
        Shower shower=new Shower(0,0,folderId,"file",showerById.getUsername(),url,originalFilename,"no",new Date(System.currentTimeMillis()));
        //再保存到数据库中
        showerDao.addShower(shower);

        return true;
    }

    public Shower downLoadFileHelper(int id){
            return showerDao.getShowerById(id);
    }
}

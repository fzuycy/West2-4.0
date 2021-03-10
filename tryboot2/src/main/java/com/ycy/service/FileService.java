package com.ycy.service;

import com.ycy.dao.ShowerDaoImpl;
import com.ycy.pojo.Shower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

@Service
public class FileService {

    @Autowired
    private  ShowerDaoImpl showerDao;

    public boolean upLoadFile(String username, String filename, MultipartFile myfile) throws IOException {
            //获取源文件后缀名
            String originalFilename = myfile.getOriginalFilename();
            int lastIndexOf=originalFilename.lastIndexOf(".");
            String substring = originalFilename.substring(lastIndexOf);
            System.out.println("文件名后缀为"+substring);
            //保存到文件服务器或OSS服务器
            String url="";
            if(StringUtils.hasLength(filename))
            {
                filename=filename+substring;
                url="D:\\pictures\\"+username+"\\"+filename;
            }

            else
            {
                filename=originalFilename;
                url="D:\\pictures\\"+username+"\\"+originalFilename;
            }
            if(showerDao.queryForFile(url)!=null){
                System.out.println("该文件名已存在，请改名");
                return false;
            }
//                myfile.transferTo(new File("D:\\pictures\\"+originalFilename));
            else {
                //保存到磁盘中
                myfile.transferTo(new File(url));
                //同时保存到数据库中
                Shower shower=new Shower(0,"file",url,new Date(System.currentTimeMillis()),filename,username,"no");
                showerDao.addShower(shower);
                System.out.println("文件上传成功");
                return true;
            }
    }

    public Shower downLoadFile(Shower shower){
        return showerDao.getMyShower(shower);
    }
}

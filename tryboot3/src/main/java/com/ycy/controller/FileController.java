package com.ycy.controller;

import com.ycy.pojo.Shower;
import com.ycy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/loadpage")
    public String LoadPage(){
        return "form";
    }


//    @ResponseBody
//    @PostMapping("/upload")//文件上传
//    public Boolean FileUpLoad(@RequestParam("name")String ownerName,@RequestParam("newFilename")String myNewFileName, @RequestParam("file")MultipartFile myfile) throws IOException {
////@RequestParam(value = "name",required = false,defaultValue = "杨潮湧")String ownerName,@RequestParam(value = "newFilename",required = false,defaultValue = "我的图片")String myNewFileName,
//        System.out.println("文件上传");
//        System.out.println("上传用户信息:"+ownerName+" "+"重命名:"+myNewFileName+"文件大小："+myfile.getSize());
//
//        System.out.println(myNewFileName.length());
//
//        if(!myfile.isEmpty()){
//             return  fileService.upLoadFile(ownerName,myNewFileName,myfile);
//        }
//
//        return false;
//    }


    @ResponseBody
    @PostMapping("/upload")//文件上传
    public boolean FileUpLoad(@RequestParam("FolderId")int folderId, @RequestParam("file")MultipartFile myfile) throws IOException {
//@RequestParam(value = "name",required = false,defaultValue = "杨潮湧")String ownerName,@RequestParam(value = "newFilename",required = false,defaultValue = "我的图片")String myNewFileName,
        System.out.println("文件上传");
        System.out.println("文件大小："+myfile.getSize());
//这边要注意一下上传的文件名可能会很长，数据库的表可能放不下这么长的字段（这里可以叫前端写一个重命名的操作（限制字数））

        return fileService.upLoadFile(folderId,myfile);
//这里可以进行文件格式限制！！！！！！！！！！！！！！！
    }

    //文件下载
    @ResponseBody
    @GetMapping("/download")
    public String FileDownLoad(@RequestParam("myId")int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("文件的编号为："+id);
        response.setHeader("content-type","image/jpg");
        response.setContentType("application/octet-stream");
        Shower shower = fileService.downLoadFileHelper(id);
        String fileName=shower.getSname();//获取文件名

        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName,"UTF-8"));
        byte[] buff=new byte[1024];
        BufferedInputStream bis=null;
        OutputStream outputStream =null;
        outputStream=response.getOutputStream();
        String url=shower.getPath();
        bis=new BufferedInputStream(new FileInputStream(new File(url)));//直接拿到图片路径获取流信息

        int read=bis.read(buff);

        while (read != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            read = bis.read(buff);
        }
        if(bis!=null)
        {
            bis.close();
        }
        if(outputStream!=null){
            outputStream.close();
        }
        return "文件下载成功";
    }

}


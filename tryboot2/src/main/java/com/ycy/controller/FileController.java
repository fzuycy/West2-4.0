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


    @GetMapping("/downloadPage")
    public String DownLoadPage(){return "Download";}

    @GetMapping("/uploadpage")
    public String UpLoadPage(){
        return "form";
    }



    @ResponseBody
    @PostMapping("/upload")//文件上传
    public Boolean FileUpLoad(@RequestParam("name")String ownerName,@RequestParam("newFilename")String myNewFileName, @RequestParam("file")MultipartFile myfile)  {
        //这里的myNewFileName是前端提示用户输入的重命名文件名，因为通常文件名可能会很长，所以需要重命名一下，如果用户没重命名的话，这里会采用originalFilename
        System.out.println("文件上传");
        System.out.println("上传用户信息:"+ownerName+" "+"重命名:"+myNewFileName+"文件大小："+myfile.getSize());

        System.out.println(myNewFileName.length());

        if(!myfile.isEmpty()){
            try {
                return  fileService.upLoadFile(ownerName,myNewFileName,myfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    //文件下载
    @ResponseBody
    @PostMapping("/download")
    public String FileDownLoad(Shower shower,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-type","image/jpg");
        response.setContentType("application/octet-stream");
        System.out.println("下载者为:"+shower+"文件名为："+shower.getSname());

        Shower downLoadFile = fileService.downLoadFile(shower);
        if(downLoadFile==null)//找不到指定路径下的文件
            return "文件下载失败";
        System.out.println("查询结果是："+downLoadFile);
        String fileName=downLoadFile.getSname();//获取文件名

        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName,"UTF-8"));//这边这个文件名fileName中必须要含有对应后缀，不然下载下来会非常奇怪
        byte[] buff=new byte[1024];
        BufferedInputStream bis=null;
        OutputStream outputStream =null;
        outputStream=response.getOutputStream();
        String url=downLoadFile.getPath();
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


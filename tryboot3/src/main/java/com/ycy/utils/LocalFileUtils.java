package com.ycy.utils;

import java.io.File;

public class LocalFileUtils {
//这边写的是一些在磁盘上新建文件夹和删除文件之类操作的工具类
    public static void CreateFolder(String path){
        File file=new File(path);
        file.mkdirs();
    }

    public static void DeleteFile(String path){
        File file=new File(path);
        if(file.exists()){
            file.delete();
            System.out.println("文件删除成功");
        }
        else {
            System.out.println("文件不存在！");
        }
    }
}

package com.ycy.utils;

import java.io.File;

public class LocalFileUtils {

    public static void CreateFolder(String path){
        File file=new File(path);
        file.mkdir();
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

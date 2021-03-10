package com.ycy.pojo;

import java.sql.Date;

public class Shower {//用Shower作为表shower的bean（通用）,通过mytype来判别是文件夹还是文件

    int id;
    String mytype;//类型
    String path;//路径  path,mydate,sname,username,ischecked
    Date mydate;//日期
    String sname;//名字
    String username;//用户名
    String ischecked;//是否通过审核 null说明是文件夹，只有文件该参数不为空

    public Shower(){

    }

    public Shower(int id, String mytype, String path, Date mydate, String sname, String username, String ischecked) {
        this.id = id;
        this.mytype = mytype;
        this.path = path;
        this.mydate = mydate;
        this.sname = sname;
        this.username = username;
        this.ischecked = ischecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMytype() {
        return mytype;
    }

    public void setMytype(String mytype) {
        this.mytype = mytype;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getMydate() {
        return mydate;
    }

    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIschecked() {
        return ischecked;
    }

    public void setIschecked(String ischecked) {
        this.ischecked = ischecked;
    }

    @Override
    public String toString() {
        return "Shower{" +
                "id=" + id +
                ", mytype='" + mytype + '\'' +
                ", path='" + path + '\'' +
                ", mydate=" + mydate +
                ", sname='" + sname + '\'' +
                ", username='" + username + '\'' +
                ", ischecked='" + ischecked + '\'' +
                '}';
    }
}


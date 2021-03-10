package com.ycy.pojo;

import java.sql.Date;

public class Shower {//用Shower作为表shower的bean（通用）,通过mytype来判别是文件夹还是文件
//最开始设计表时，我本来想把文件和文件夹分开，但后来发现其实两者有很多相似的属性，于是就简单粗暴地将两者归为一个表里了
    int id;//为了前端那边处理方便加的属性
    int myid;//自身id
    int parentid;//父id
    String mytype;//对象类型
    String username;//用户名
    String path;//路径  myid,parentid,mytype,username,path,sname,ischecked,mydate
    String sname;//名字
    String ischecked;//是否通过审核 null说明是文件夹，只有文件该参数不为空
    Date mydate;//日期

    public Shower(){

    }


    public Shower(int id, int myid, int parentid, String mytype, String username, String path, String sname, String ischecked, Date mydate) {
        this.id = id;
        this.myid = myid;
        this.parentid = parentid;
        this.mytype = mytype;
        this.username = username;
        this.path = path;
        this.sname = sname;
        this.ischecked = ischecked;
        this.mydate = mydate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getMytype() {
        return mytype;
    }

    public void setMytype(String mytype) {
        this.mytype = mytype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIschecked() {
        return ischecked;
    }

    public void setIschecked(String ischecked) {
        this.ischecked = ischecked;
    }

    public Date getMydate() {
        return mydate;
    }

    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }

    @Override
    public String toString() {
        return "Shower{" +
                "id=" + id +
                ", myid=" + myid +
                ", parentid=" + parentid +
                ", mytype='" + mytype + '\'' +
                ", username='" + username + '\'' +
                ", path='" + path + '\'' +
                ", sname='" + sname + '\'' +
                ", ischecked='" + ischecked + '\'' +
                ", mydate=" + mydate +
                '}';
    }
}


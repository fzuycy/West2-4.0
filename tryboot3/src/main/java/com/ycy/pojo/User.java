package com.ycy.pojo;

public class User {
    private String username;
    private String userpsw;
    private String usertel;
    private int rootFolderId;

    public User(){

    }

    public User(String username, String userpsw, String usertel, int rootFolderId) {
        this.username = username;
        this.userpsw = userpsw;
        this.usertel = usertel;
        this.rootFolderId = rootFolderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public int getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(int rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userpsw='" + userpsw + '\'' +
                ", usertel='" + usertel + '\'' +
                ", rootFolderId=" + rootFolderId +
                '}';
    }
}

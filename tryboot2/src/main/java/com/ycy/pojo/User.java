package com.ycy.pojo;

public class User {
    private String username;
    private String userpsw;
    private String usertel;

    public User(){

    }
    public User(String username, String userpsw, String usertel) {
        this.username = username;
        this.userpsw = userpsw;
        this.usertel = usertel;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userpsw='" + userpsw + '\'' +
                ", usertel='" + usertel + '\'' +
                '}';
    }
}

package com.example.bookkeeping;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "user_info_table")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int userInfo_id;

    private String currentUser;

    private int height;

    private int age;

    public UserInfo(String currentUser, int height, int age) {
        this.currentUser = currentUser;
        this.height = height;
        this.age = age;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserInfo_id() {
        return userInfo_id;
    }

    public void setUserInfo_id(int userInfo_id) {
        this.userInfo_id = userInfo_id;
    }
}

package com.example.bookkeeping;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class userdetails {
    @PrimaryKey(autoGenerate =  true)
    Integer id;


    @ColumnInfo(name = "userid")
    String userid22;

    @ColumnInfo(name = "username")
    String username;

    @ColumnInfo(name = "password")
    String password;

    public Integer getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setuserIDRIVI(String useridrivi){
        this.userid22 = useridrivi;
    }

    public String getuserIDRIVI(){
        return userid22;
    }
}

package com.example.bookkeeping;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//defining room entity
@Entity(tableName = "users")
public class userdetails {
    @PrimaryKey(autoGenerate =  true)
    Integer id;


    @ColumnInfo(name = "userid",typeAffinity = ColumnInfo.BLOB)
    byte[] userid22;

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

    public void setuserIDRIVI(byte[] useridrivi){
        this.userid22 = useridrivi;
    }

    public byte[] getuserIDRIVI(){
        return userid22;
    }
}

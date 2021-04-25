package com.example.bookkeeping;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserAndInfo {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "currentUser"
    )
    public UserInfo userinfo;

}

package com.example.wellbeing;

import androidx.room.Embedded;
import androidx.room.Relation;
// one to one relation for user information
public class UserAndInfo {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "currentUser"
    )
    public UserInfo userinfo;

}

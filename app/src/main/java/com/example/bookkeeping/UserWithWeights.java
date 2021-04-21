package com.example.bookkeeping;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithWeights {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "currentUser"
    )
    public List<Weight> allWeights;

}

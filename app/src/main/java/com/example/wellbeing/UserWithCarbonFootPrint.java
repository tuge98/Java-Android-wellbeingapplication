package com.example.wellbeing;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
// One to many relation for person's carbon footprint
public class UserWithCarbonFootPrint {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "currentuser"
    )
    public List<CarbonFootPrint> allCFPs;

}

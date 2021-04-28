package com.example.wellbeing;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// Weight table and class
@Entity(tableName = "weight_table")
public class Weight {
    @PrimaryKey(autoGenerate = true)
    Integer weight_id;

    String currentUser;

    String date;

    Double weight;

    public Weight(String currentUser, String date, Double weight) {
        this.currentUser = currentUser;
        this.date = date;
        this.weight = weight;
    }

    public Integer getWeight_id() {
        return weight_id;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getDate() {
        return date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWid(Integer weight_id) {
        this.weight_id = weight_id;
    }
}
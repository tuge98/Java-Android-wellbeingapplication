package com.example.wellbeing;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// carbon footprint table and class
@Entity(tableName = "cfp_table")
public class CarbonFootPrint {

    @PrimaryKey(autoGenerate = true)
    int cfp_id;

    String currentuser;

    double dairy;

    double meat;

    double plant;

    double restaurant;

    double total;

    public CarbonFootPrint(String currentuser, double dairy, double meat, double plant, double restaurant, double total) {
        this.currentuser = currentuser;
        this.dairy = dairy;
        this.meat = meat;
        this.plant = plant;
        this.restaurant = restaurant;
        this.total = total;
    }

    public int getCfp_id() {
        return cfp_id;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public double getDairy() {
        return dairy;
    }

    public double getMeat() {
        return meat;
    }

    public double getPlant() {
        return plant;
    }

    public double getRestaurant() {
        return restaurant;
    }

    public double getTotal() {
        return total;
    }

    public void setCfp_id(int cfp_id) {
        this.cfp_id = cfp_id;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public void setDairy(double dairy) {
        this.dairy = dairy;
    }

    public void setMeat(double meat) {
        this.meat = meat;
    }

    public void setPlant(double plant) {
        this.plant = plant;
    }

    public void setRestaurant(double restaurant) {
        this.restaurant = restaurant;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}



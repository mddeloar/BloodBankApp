package com.example.mddeloarhossain.bloodbank;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Created by MD. DELOAR HOSSAIN on 30-Apr-19.
 */

public class DonorR {
    private String name;
    private String city;
    private String bloodgroup;
    private String image;

    public DonorR(){

    }

    public DonorR(String name, String city, String bloodrgroup, String image) {
        this.name = name;
        this.city = city;
        this.bloodgroup = bloodgroup;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image) {
        this.image = image;
    }
    /*public void setImage(Context ctx, String image) {
        this.image = image;
        //Picasso.with(ctx).load(image).into(image);
    }*/
}

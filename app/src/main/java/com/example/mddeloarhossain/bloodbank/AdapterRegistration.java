package com.example.mddeloarhossain.bloodbank;

import com.google.firebase.database.Exclude;

/**
 * Created by MD. DELOAR HOSSAIN on 30-Apr-19.
 */

public class AdapterRegistration {
    private String imageName;
    private String name;
    private String imageUrl;
    //private String name;
    private String city;
    private String location;
    private String age;
    private String contactnumber;

    private String bloodgroup;
    private String gender;
    private String inTheList;

    private String key;

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public AdapterRegistration(){

    }

    public AdapterRegistration(String imageName, String name, String city, String location, String age, String contactnumber, String bloodgroup, String gender, String inTheList, String imageUrl) {
        this.imageName = imageName;
        this.name = name;
        this.imageUrl = imageUrl;
        this.city = city;
        this.location = location;
        this.age = age;
        this.contactnumber = contactnumber;
        this.bloodgroup = bloodgroup;
        this.gender = gender;
        this.inTheList = inTheList;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInTheList() {
        return inTheList;
    }

    public void setInTheList(String inTheList) {
        this.inTheList = inTheList;
    }
}

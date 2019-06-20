package com.example.mddeloarhossain.bloodbank;

        import com.google.firebase.database.Exclude;

/**
 * Created by MD. DELOAR HOSSAIN on 30-Apr-19.
 */

public class UploadWithDetails {
    private String imageName;
    private String name;
    private String imageUrl;

    private String key;

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public UploadWithDetails(){

    }

    public UploadWithDetails(String imageName, String name, String imageUrl) {
        this.imageName = imageName;
        this.name = name;
        this.imageUrl = imageUrl;
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
}

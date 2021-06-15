package com.example.facebook.dto;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;

public class ProfileDTO extends Profile {

    private String userFullName;
    private Boolean isFullNamePublic;
    private String otherInfo;
    private Image image;

    public ProfileDTO() {

    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Boolean getFullNamePublic() {
        return isFullNamePublic;
    }

    public void setFullNamePublic(Boolean fullNamePublic) {
        isFullNamePublic = fullNamePublic;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}

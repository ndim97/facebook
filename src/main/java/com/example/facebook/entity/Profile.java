package com.example.facebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    private String userFullName;

    @Column(name = "is_full_name_public")
    private Boolean isFullNamePublic;

    @Column(name = "other_info")
    private String otherInfo;

    @OneToOne(targetEntity = Image.class, optional = false)
    private Image image;

    public Profile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

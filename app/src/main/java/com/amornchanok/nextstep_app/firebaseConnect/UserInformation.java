package com.amornchanok.nextstep_app.firebaseConnect;

public class UserInformation {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String UserType;
    private String imageProfile;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() { return UserType; }
    public void setUserType(String userType) { this.UserType = userType; }

    public String getImageProfile() { return imageProfile; }
    public void setImageProfile(String imageProfile) { this.imageProfile = imageProfile; }
}

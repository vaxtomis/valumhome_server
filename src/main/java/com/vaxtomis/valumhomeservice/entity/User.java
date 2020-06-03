package com.vaxtomis.valumhomeservice.entity;

public class User {
    private String userAccount;
    private String userPassword;
    private String userEmail;
    private int userId;
    private int userHomeId;

    public User() {
    }

    public User(String userAccount, String userPassword, String userEmail) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public User(String userAccount, String userPassword, String userEmail, int userId, int userHomeId) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userHomeId = userHomeId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserHomeId() {
        return userHomeId;
    }

    public void setUserHomeId(int userHomeId) {
        this.userHomeId = userHomeId;
    }
}

package com.mywings.patients;

import java.util.List;

public class UserDataHolder {

    private Doctor doctor;

    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public static UserDataHolder getInstance() {
        return UserDataHolderHelper.INSTANCE;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class UserDataHolderHelper {

        static UserDataHolder INSTANCE = new UserDataHolder();

    }


}

package com.mywings.patients;

import java.util.List;

public class UserDataHolder {

    private boolean admin;

    private Doctor doctor;

    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    private User selectedUser;

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

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static class UserDataHolderHelper {

        static UserDataHolder INSTANCE = new UserDataHolder();

    }


}

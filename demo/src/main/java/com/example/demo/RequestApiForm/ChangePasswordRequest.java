package com.example.demo.RequestApiForm;

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;

    // Ajoutez des constructeurs, des getters et des setters ici

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

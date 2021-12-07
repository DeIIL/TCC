package com.example.tcc.models;

public class User {
    private int user_id;
    private String user_cpf;
    private String user_phone;
    private String user_name;

    public User(String user_cpf, String user_phone, String user_name, String user_email, String user_password) {
        this.user_cpf = user_cpf;
        this.user_phone = user_phone;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    private String user_email;
    private String user_password;
    private String user_img;
    private String user_lvl;
    private int fk_address_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_lvl() {
        return user_lvl;
    }

    public void setUser_lvl(String user_lvl) {
        this.user_lvl = user_lvl;
    }

    public int getFk_address_id() {
        return fk_address_id;
    }

    public void setFk_address_id(int fk_address_id) {
        this.fk_address_id = fk_address_id;
    }

}

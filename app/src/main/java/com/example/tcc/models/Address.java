package com.example.tcc.models;

public class Address {

    private int address_id;
    private String address_cep;
    private String address_uf;
    private String address_city;
    private String address_district;
    private String address_public_place;
    private String address_complement;
    private int fk_user_id;

    public Address(String address_cep, String address_uf, String address_city, String address_district, String address_public_place, String address_complement) {
        this.address_cep = address_cep;
        this.address_uf = address_uf;
        this.address_city = address_city;
        this.address_district = address_district;
        this.address_public_place = address_public_place;
        this.address_complement = address_complement;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_cep() {
        return address_cep;
    }

    public void setAddress_cep(String address_cep) {
        this.address_cep = address_cep;
    }

    public String getAddress_uf() {
        return address_uf;
    }

    public void setAddress_uf(String address_uf) {
        this.address_uf = address_uf;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_district() {
        return address_district;
    }

    public void setAddress_district(String address_district) {
        this.address_district = address_district;
    }

    public String getAddress_public_place() {
        return address_public_place;
    }

    public void setAddress_public_place(String address_public_place) {
        this.address_public_place = address_public_place;
    }

    public String getAddress_complement() {
        return address_complement;
    }

    public void setAddress_complement(String address_complement) {
        this.address_complement = address_complement;
    }

    public int getFk_user_id() {
        return fk_user_id;
    }

    public void setFk_user_id(int fk_user_id) {
        this.fk_user_id = fk_user_id;
    }
}

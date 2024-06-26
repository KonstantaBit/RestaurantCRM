package org.m0d3rn1ca.restaurantcrm;

import javafx.beans.property.*;

public class Person {
    private SimpleIntegerProperty ID;
    private SimpleStringProperty last_name;
    private SimpleStringProperty first_name;
    private SimpleStringProperty patronymic;
    private SimpleStringProperty address;
    private SimpleStringProperty ITN;
    private SimpleStringProperty phone_number;
    private SimpleStringProperty login;

    public Person(Integer ID, String last_name, String first_name, String patronymic, String residential_address, String ITN, String phone_number, String login) {
        this.ID = new SimpleIntegerProperty(ID);
        this.last_name = new SimpleStringProperty(last_name);
        this.first_name = new SimpleStringProperty(first_name);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.address = new SimpleStringProperty(residential_address);
        this.ITN = new SimpleStringProperty(ITN);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.login = new SimpleStringProperty(login);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getLast() {
        return last_name.get();
    }

    public void setLast(String last_name) {
        this.last_name.set(last_name);
    }

    public String getFirst() {
        return first_name.get();
    }

    public void setFirst(String first_name) {
        this.first_name.set(first_name);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String residential_address) {
        this.address.set(residential_address);
    }

    public String getITN() {
        return ITN.get();
    }

    public void setITN(String ITN) {
        this.ITN.set(ITN);
    }

    public String getPhone() {
        return phone_number.get();
    }

    public void setPhone(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }
}
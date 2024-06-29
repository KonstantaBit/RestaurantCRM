package org.m0d3rn1ca.restaurantcrm;

public class Order {
    private int ID;
    private String code;
    public Order(int ID, String code) {
        this.ID = ID;
        this.code = code;
    }

    public int getID() {
        return ID;
    }

    public String getCode() {
        return code;
    }
}

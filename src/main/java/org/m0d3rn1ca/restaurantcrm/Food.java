package org.m0d3rn1ca.restaurantcrm;

import java.util.ArrayList;

public class Food {
    private int ID;
    private String name;

    public Food(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
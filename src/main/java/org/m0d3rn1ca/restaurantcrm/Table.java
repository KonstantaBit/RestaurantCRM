package org.m0d3rn1ca.restaurantcrm;

import javafx.beans.property.*;

public class Table  {
    private int ID;
    private int capacity;

    public Table(int ID, int capacity) {
        this.ID = ID;
        this.capacity = capacity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
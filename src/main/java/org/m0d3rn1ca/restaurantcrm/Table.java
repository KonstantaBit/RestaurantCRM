package org.m0d3rn1ca.restaurantcrm;

import javafx.beans.property.*;

public class Table  {
    private SimpleIntegerProperty ID;
    private SimpleIntegerProperty capacity;

    public Table(int ID, int capacity) {
        this.ID = new SimpleIntegerProperty(ID);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }
}
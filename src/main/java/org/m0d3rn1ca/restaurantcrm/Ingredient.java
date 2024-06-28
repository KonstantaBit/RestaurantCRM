package org.m0d3rn1ca.restaurantcrm;

public class Ingredient {
    private int ID;
    private String unit;
    private String name;
    private float amount;

    public Ingredient(int ID, String unit, String name, float amount) {
        this.ID = ID;
        this.unit = unit;
        this.name = name;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
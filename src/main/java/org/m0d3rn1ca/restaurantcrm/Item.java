package org.m0d3rn1ca.restaurantcrm;

public class Item {
    private int ID;
    private int order_id;
    private int food_id;
    private int amount;
    public Item(int ID, int order_id, int food_id, int amount) {
        this.ID = ID;
        this.order_id = order_id;
        this.food_id = food_id;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public int getOrderId() {
        return order_id;
    }

    public int getFoodId() {
        return food_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return Foods.getInstance().getFood(food_id).getName();
    }
}

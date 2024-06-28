package org.m0d3rn1ca.restaurantcrm;

public class Recipe {
    private int ID;
    private float amount;
    private int food_id;
    private int ingredient_id;
    public Recipe(int ID, int amount, int food_id, int ingredient_id) {
        this.ID = ID;
        this.amount = amount;
        this.food_id = food_id;
        this.ingredient_id = ingredient_id;
    }

    public int getID() {
        return ID;
    }
    public float getAmount() {
        return amount;
    }
    public int getFoodId() {
        return food_id;
    }
    public int getIngredientId() {
        return ingredient_id;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getName() {
        return Ingredients.getInstance().getIngredient(ingredient_id).getName();
    }
    public String getUnit() {
        return Ingredients.getInstance().getIngredient(ingredient_id).getUnit();
    }
}
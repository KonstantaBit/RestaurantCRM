package org.m0d3rn1ca.restaurantcrm;

public class Position {
    private int ID;
    private int user_id;
    private int role_id;
    public Position(int ID, int user_id, int role_id) {
        this.ID = ID;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public int getID() {
        return ID;
    }

    public int getRoleId() {
        return role_id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getName() {
        return Roles.getInstance().getRole(role_id).getName();
    }
}

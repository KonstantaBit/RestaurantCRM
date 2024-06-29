package org.m0d3rn1ca.restaurantcrm;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Eating {
    private int ID;
    private int table_id;
    private int waiter_id;
    private int order_id;
    private Timestamp timestamp;
    private int delta;

    public Eating(int ID, int table_id, int waiter_id, int order_id, Timestamp timestamp, int delta) {
        this.ID = ID;
        this.table_id = table_id;
        this.waiter_id = waiter_id;
        this.order_id = order_id;
        this.timestamp = timestamp;
        this.delta = delta;
    }

    public int getID() {
        return ID;
    }

    public int getTableId() {
        return table_id;
    }

    public int getWaiterId() {
        return waiter_id;
    }

    public int getOrderId() {
        return order_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getDelta() {
        return delta;
    }
}

package org.m0d3rn1ca.restaurantcrm;

import java.util.ArrayList;
import java.util.HashMap;

public class Cache {
    private final int id_of_query;
    private final ArrayList<String> input;
    private final ArrayList<HashMap<String, Object>> output;

    public Cache(int id_of_query, ArrayList<String> input, ArrayList<HashMap<String, Object>> output) {
        this.id_of_query = id_of_query;
        this.input = input;
        this.output = output;
    }

    public int getId_of_query() {
        return id_of_query;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public ArrayList<HashMap<String, Object>> getOutput() {
        return output;
    }
}

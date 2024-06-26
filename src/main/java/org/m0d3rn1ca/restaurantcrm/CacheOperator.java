package org.m0d3rn1ca.restaurantcrm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CacheOperator {
    private ArrayList<Cache> caches = null;

    public CacheOperator() {
        this.caches = new ArrayList<>();
    }

    public ArrayList<HashMap<String, Object>> getCache(int id_of_query, ArrayList<String> input) {
        for (Cache cache : caches)
            if (cache.getId_of_query() == id_of_query && cache.getInput().equals(input))
                return cache.getOutput();
        return null;
    }

    public boolean checkCache(int id_of_query, ArrayList<String> input) {
        for (Cache cache : caches)
            if (cache.getId_of_query() == id_of_query && cache.getInput().equals(input))
                return true;
        return false;
    }

    public void flushCache() {
        caches.clear();
    }

//    public boolean contains(Cache cache) {
//        return caches.contains(cache);
//    }

    public void removeCache(int id_of_query) {
        System.out.println(id_of_query);
        caches.removeIf(cache -> cache.getId_of_query() == id_of_query);
    }

//    public void addCache(Cache cache) {
//        caches.add(cache);
//    }

    public ArrayList<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; ++i)
                row.put(md.getColumnName(i), rs.getObject(i));
            list.add(row);
        }
        return list;
    }

    public void addCache(int query_id, ArrayList<String> input, ResultSet rs) throws SQLException {
        if (rs == null)
            caches.add(new Cache(query_id, input, null));
        else
            caches.add(new Cache(query_id, input, convertResultSetToList(rs)));
    }

    public void addCache(int query_id, ArrayList<String> input, ArrayList<HashMap<String, Object>> output) throws SQLException {
        caches.add(new Cache(query_id, input, output));
    }

    public ArrayList<HashMap<String, Object>> getCaches(int id_of_query) {
        ArrayList<HashMap<String, Object>> response = new ArrayList<>();
        for (Cache cache : caches)
            if (cache.getId_of_query() == id_of_query)
                response.addAll(cache.getOutput());
        return response;
    }
}

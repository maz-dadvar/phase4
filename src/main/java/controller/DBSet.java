package controller;

import java.util.LinkedList;

public interface DBSet<T> {

    static String jdbcURL = "jdbc:postgresql://localhost:5432/twitter";
    static String userName = "postgres";
    static String passWord = "saleh791378";

    T get(int id);
    LinkedList<T> all();
    void add(T t);
    void remove(T t);
    void update(T t);
}

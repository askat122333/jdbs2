package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.getKafeName();
        main.getDishName();

    }

    private final String URL = "jdbc:postgresql://localhost:5432/jdbs2";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    public Connection connect () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void getKafeName() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement st = conn.createStatement();
            String sql = "select count(kafe_id) from orders where kafe_id = 1;";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            System.out.println("количество заказов = "+rs.getInt(1));
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDishName() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement st = conn.createStatement();
            String sql = "select dish_name , sum(dish_count)from orders\n" +
                    "join dish d on d.id = orders.dish_id\n" +
                    "group by dish_name";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            System.out.println("Название самого популярного блюда = "+rs.getString(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
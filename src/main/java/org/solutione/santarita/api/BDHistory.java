package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDHistory {
    private Connection conn;
    public BDHistory(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<History> getHistory(){
        ObservableList<History> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM history";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                double price = rs.getDouble("price");
                double benefit = rs.getDouble("benefit");
                String date = rs.getString("date");

                datos.add(new History(code,name,cost,price,benefit,date));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
    public void addHistory(String code, String name, double cost, double price, double benefit,String date){
        try {
            String query = "insert into history " +
                    "(code,name,cost,price,benefit,date) " +
                    "values (" +
                    "'"+code+"'," +
                    "'"+name+"'," +
                    ""+cost+"," +
                    ""+price+"," +
                    ""+benefit+"," +
                    "'"+date+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

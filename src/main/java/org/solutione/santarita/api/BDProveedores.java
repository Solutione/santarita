package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDProveedores {
    private Connection conn;
    public BDProveedores(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Proveedor> getProviders(){
        ObservableList<Proveedor> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM providers";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String codes = rs.getString("codes");
                String img =  rs.getString("img");

                datos.add(new Proveedor(name,brand,codes,img));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
    public Proveedor getProvider(String name){
        Proveedor provider = null;
        try {
            String query = String.format("SELECT * FROM providers where name = '%s'", name);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                provider = new Proveedor(name,
                        rs.getString("brand"),
                        rs.getString("codes"),
                        rs.getString("img"));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provider;
    }
    public void addProvider(String name,String brand,String codes,String img){
        try {
            String query = "insert into providers " +
                    "(name,brand,codes,img) " +
                    "values (" +
                    "'"+name+"'," +
                    "'"+brand+"'," +
                    "'"+codes+"'," +
                    "'"+img+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProvider(String oldName, String name,String brand,String codes,String img){
        try {
            String query = "UPDATE providers SET " +
                    "name = '"+name+"'," +
                    "brand = '"+brand+"'," +
                    "codes = '"+codes+"'," +
                    "img = '"+img +"'"+
                    "where name = '"+oldName+"'";

            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

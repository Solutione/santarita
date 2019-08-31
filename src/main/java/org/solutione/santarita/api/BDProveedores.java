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
    public String[] getProvider(String name){
        String[] provider = new String[4];
        try {
            String query = "SELECT * FROM providers where name = '"+name+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                provider[0] = name;
                provider[1] = rs.getString("brand");
                provider[2] = rs.getString("codes");
                provider[3] = rs.getString("img");
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
    public void setProvider(String name,String brand,String codes,String img){
        try {
            String query = "UPDATE providers SET " +
                    "name = '"+name+"'," +
                    "brand = '"+brand+"'," +
                    "codes = '"+codes+"'," +
                    "img = '"+img +"'"+
                    "where name = '"+name+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getCodes(String name){
        String cds = "";
        try {
            String query = "SELECT codes FROM providers WHERE name = '"+name+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                cds = rs.getString("codes");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] codes = cds.split(",");

        return codes;
    }
}

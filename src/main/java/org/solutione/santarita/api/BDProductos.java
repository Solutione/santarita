package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDProductos {
    private Connection conn;
    public BDProductos(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public ObservableList<Producto> getProducts(){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM products";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                double price = rs.getDouble("price");
                double units = rs.getDouble("units");
                String brand = rs.getString("brand");
                String expiration = rs.getString("expiration");

                datos.add(new Producto(code,name,cost,price,units,brand,expiration));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
    public Producto getProduct(String code){
        Producto product =  null;
        try {
            String query = "SELECT * FROM products where code = '"+code+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                product = new Producto(code,
                        rs.getString("name"),
                        rs.getDouble("cost"),
                        rs.getDouble("price"),
                        rs.getDouble("units"),
                        rs.getString("brand"),
                        rs.getString("expiration"));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public void updateProduct(String code, double units){
        try {
            String query = "UPDATE products SET units = "+units+" where code = '"+code+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduct(String codigo,String nombre,double costo,double precio, double unidades,String marca,String caducidad){
        try {
            String query = "insert into products " +
                    "(code,name,cost,price,units,brand,expiration) " +
                    "values (" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    ""+costo+"," +
                    ""+precio+"," +
                    ""+unidades+"," +
                    "'"+marca+"'," +
                    "'"+caducidad+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProduct(String codigo,String nombre,double costo,double precio, double unidades,String marca,String caducidad){
        try {
            String query = "UPDATE products SET " +
                    "code = '"+codigo+"'," +
                    "name = '"+nombre+"'," +
                    "cost = "+costo+"," +
                    "price = "+precio+"," +
                    "units = "+unidades+"," +
                    "brand = '"+marca+"'," +
                    "expiration = '"+caducidad+"' " +
                    "where code = '"+codigo+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getCode(String name){
        String code = "";
        try {
            String query = "SELECT code FROM products where name = '"+name+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                code = rs.getString("code");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
    public ObservableList<Producto> getSaleProduct(String fecha){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT name,cost,price,benefit FROM history where date = '"+fecha+"';";        
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String name = rs.getString("name");
                double units = rs.getDouble("cost");
                double price = rs.getDouble("price");
                double benefit = rs.getDouble("benefit");
              

                datos.add(new Producto(name,units,price,benefit));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
     }
}

package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDVarProducts {
    private Connection conn;
    public BDVarProducts(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Producto> getProducts(){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM products_var";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String code = rs.getString("code");
                String name = rs.getString("name");

                datos.add(new Producto(code,name));
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
            String query = "SELECT * FROM products_var where code = '"+code+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                product = new Producto(code,rs.getString("name"));
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
            String query = "UPDATE products_var SET units = "+units+" where code = '"+code+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteProduct(String code){
        try {
            String query = "DELETE FROM products_var WHERE code = '"+code+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduct(String codigo,String nombre){
        try {
            String query = "insert into products_var " +
                    "(code,name) " +
                    "values (" +
                    "'"+codigo+"'," +
                    "'"+nombre+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProduct(String codigo,String nombre){
        try {
            String query = "UPDATE products SET " +
                    "code = '"+codigo+"'," +
                    "name = '"+nombre+"' " +
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
}

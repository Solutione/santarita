package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDProductos {
    private Connection conn;
    public BDProductos(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Producto> getProducts(){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Products";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                double price = rs.getDouble("price");
                int units = rs.getInt("units");
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
    public String[] getProduct(String code){
        String[] product = new String[7];
        try {
            String query = "SELECT * FROM Products where code = '"+code+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                product[0] = code;
                product[1] = rs.getString("name");;
                product[2] = Double.toString(rs.getDouble("cost"));
                product[3] = Double.toString(rs.getDouble("price"));
                product[4] = Integer.toString(rs.getInt("units"));
                product[5] = rs.getString("brand");
                product[6] = rs.getString("expiration");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public void updateProduct(String code, int units){
        try {
            String query = "UPDATE Products SET units = "+units+" where code = '"+code+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduct(String codigo,String nombre,double costo,double precio, int unidades,String marca,String caducidad){
        try {
            String query = "insert into Products " +
                    "(code,name,cost,price,units,brand,expiration) " +
                    "values (" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    ""+costo+"," +
                    ""+precio+"," +
                    ""+unidades+"," +
                    "'"+nombre+"'," +
                    "'"+caducidad+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProduct(String codigo,String nombre,double costo,double precio, int unidades,String marca,String caducidad){
        try {
            String query = "UPDATE Products SET " +
                    "code = '"+codigo+"'," +
                    "name = '"+nombre+"'," +
                    "cost = "+costo+"," +
                    "price = "+precio+"," +
                    "units = "+unidades+"," +
                    "brand = '"+marca+"'," +
                    "expiration = '"+caducidad+"'" +
                    "where code = '"+codigo+"'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

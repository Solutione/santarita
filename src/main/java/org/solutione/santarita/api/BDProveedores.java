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
    public ObservableList<Producto> getProducts(){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Providers";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                double cost = rs.getDouble("cost");
                double price = rs.getDouble("price");

                datos.add(new Producto(code,name,brand,cost,price));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
    public String[] getProduct(String code){
        String[] product = new String[5];
        try {
            String query = "SELECT * FROM Providers where code = '"+code+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                product[0] = code;
                product[1] = rs.getString("name");
                product[2] = rs.getString("brand");
                product[3] = Double.toString(rs.getDouble("cost"));
                product[4] = Double.toString(rs.getDouble("price"));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public void addProduct(String codigo,String nombre,String marca,double costo,double precio){
        try {
            String query = "insert into Providers " +
                    "(code,name,brand,cost,price) " +
                    "values (" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    ""+marca+"," +
                    ""+costo+"," +
                    ""+precio+")";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProduct(String codigo,String nombre,String marca,double costo,double precio){
        try {
            String query = "UPDATE Providers SET " +
                    "code = '"+codigo+"'," +
                    "name = '"+nombre+"'," +
                    "brand = '"+marca+"'," +
                    "cost = "+costo+"," +
                    "price = "+precio +
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

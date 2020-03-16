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
    public void addVisitProvider(String name){
        try {
            String query = "insert into providers_visit " +
                    "(name) " +
                    "values (" +
                    "'"+name+"')";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getDateVisit(String name){
        String dateVisit= null;
        try {
            String query = String.format("select max(date) from providers_visit where name = '%s'", name);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                dateVisit = rs.getString(1);
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(dateVisit!=null){
            String year=dateVisit.substring(0, 4);
            String month=dateVisit.substring(5, 7);
            String day=dateVisit.substring(8, 10);
            dateVisit=day+"/"+month+"/"+year;
        }        
        return dateVisit;       
    }
    public ObservableList<Producto> getViewVisit(String dateVisitProvider,String nameProvider){
        ObservableList<Producto> datos = FXCollections.observableArrayList();
        try {
            String query = "select history.name, count(history.name) as units, sum(history.benefit) as benefit from history "+
                        "inner join products "+ 
                        "on history.code=products.code "+ 
                        "where products.brand=\'"+nameProvider+"\' and "+
                        "STR_TO_DATE(history.date, \'%d/%m/%Y\') >STR_TO_DATE(\'"+dateVisitProvider+"\', \'%d/%m/%Y\') "+
                        "group by history.name order by count(history.name) desc";        
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String name = rs.getString("name");
                double units = rs.getDouble("units");
                double benefit = rs.getDouble("benefit");
              

                datos.add(new Producto(name,units,benefit));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
        
    }
    public String getTotalVisit(String dateVisitProvider,String nameProvider){
        String totalVisit= null;
        try {
            String query = "select sum(history.benefit) as benefit from history "+
                        "inner join products "+ 
                        "on history.code=products.code "+ 
                        "where products.brand=\'"+nameProvider+"\' and "+
                        "STR_TO_DATE(history.date, \'%d/%m/%Y\') >STR_TO_DATE(\'"+dateVisitProvider+"\', \'%d/%m/%Y\') ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                totalVisit = rs.getString(1);
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalVisit;       
    }
    public String getTotalProducts(String dateVisitProvider,String nameProvider){
        String total= null;
        try {
            String query = "select count(history.name)  from history "+
                        "inner join products "+ 
                        "on history.code=products.code "+ 
                        "where products.brand=\'"+nameProvider+"\' and "+
                        "STR_TO_DATE(history.date, \'%d/%m/%Y\') >STR_TO_DATE(\'"+dateVisitProvider+"\', \'%d/%m/%Y\') ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                total= rs.getString(1);
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;       
    }
    
    
}

package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BDData_F {
    private Connection conn;
    public BDData_F(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTotal(double total){
        try {
            String query = "UPDATE data_f SET mount = "+total+" where type = 'total'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBenefit(double benefit){
        try {
            String query = "UPDATE data_f SET mount = "+benefit+" where type = 'benefit'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getTotal(){
        double total = 0;
        try {
            String query = "SELECT mount FROM data_f where type = 'total'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                total = rs.getDouble("mount");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public double getBenefit(){
        double benefit = 0;
        try {
            String query = "SELECT mount FROM data_f where type = 'benefit'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                benefit = rs.getDouble("mount");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return benefit;
    }
}

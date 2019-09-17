package org.solutione.santarita.api;

import java.sql.*;

public class BDUsers {
    private Connection conn;
    public BDUsers(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santarita", "root", "123Abejas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getPassword(){
        String pass = "";
        try {
            String query = "SELECT password FROM user where name = 'root'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                pass = rs.getString("password");
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
    }
    public void setPassword(String pass){
        try {
            String query = "UPDATE user SET password = "+pass+" where name = 'root'";
            Statement st = conn.createStatement();
            st.executeQuery(query);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

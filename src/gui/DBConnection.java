/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class DBConnection {
    
   static Connection con = null;
   
   public static Connection getConnection(){
   
       try {
           
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Lbms", "root", "DBMS@aroshana2024");
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       return con;
   
   
   }

    static ResultSet executeSearch(String select__from_courses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sam
 */
public class dbConfig {
   static Connection conn= null;
   public static Connection getConn(){
       try {
           conn=DriverManager.getConnection("jdbc:sqlite:GodsLove.sqlite");
            return conn;
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex);
           return null;
       }
  
   }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criminalfacedetection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;  

public class Conn {
    Connection c;
    Statement s;
    
    Conn(){
    
        
        
     try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:MYSQL://localhost:3306/criminalfacedetection","root","mohd@password"); 
            
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            e.printStackTrace();
        }  
    
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

/**
 *
 * @author ASUS
 */

import java.sql.*;

public class Connector{
    String DBurl = "jdbc:mysql://localhost/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";

    Connection koneksi;
    Statement statement;

    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
        }catch(Exception ex){
            System.out.println("Koneksi gagal");
        }
    }
}
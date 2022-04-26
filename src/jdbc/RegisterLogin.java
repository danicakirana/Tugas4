/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

/**
 *
 * @author ASUS
 */

import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterLogin extends JFrame{
    Connector connector = new Connector();

    JLabel lTitle = new JLabel("Register", SwingConstants.LEFT);
    JLabel lTitlee = new JLabel("Login", SwingConstants.RIGHT);
    
    JLabel lUsernameReg = new JLabel("Username");
    JLabel lPasswordReg = new JLabel("Password");
    JLabel lUsernameLogin = new JLabel("Username");
    JLabel lPasswordLogin = new JLabel("Password");
    
    
    JTextField tfUsernameReg = new JTextField();
    JTextField tfPasswordReg = new JTextField();
    JTextField tfUsernameLogin = new JTextField();
    JTextField tfPasswordLogin = new JTextField();
    
    JButton btnReg = new JButton("Register");
    JButton btnLogin = new JButton("Login");
    
    public RegisterLogin(){
        setTitle("Register dan Login");
        setSize(500, 500);
        setLayout(null);

        add(lTitle);
        add(lTitlee);
        add(lUsernameReg);
        add(lPasswordReg);
        add(tfUsernameReg);
        add(tfPasswordReg);
        add(btnLogin);
        add(lUsernameLogin);
        add(lPasswordLogin);
        add(tfUsernameLogin);
        add(tfPasswordLogin);
        add(btnReg);

        lTitle.setBounds(120, 55, 200, 20);
        lTitlee.setBounds(320, 55, 50, 20);

        lUsernameReg.setBounds(65, 100, 80, 20);
        lPasswordReg.setBounds(65, 180, 80, 20);

        tfUsernameReg.setBounds(65, 130, 150, 30);
        tfPasswordReg.setBounds(65, 210, 150, 30);

        btnReg.setBounds(80, 300, 100, 20);

        lUsernameLogin.setBounds(270, 100, 80, 20);
        lPasswordLogin.setBounds(270, 180, 80, 20);

        tfUsernameLogin.setBounds(290, 130, 150, 30);
        tfPasswordLogin.setBounds(290, 210, 150, 30);

        btnLogin.setBounds(300, 300, 100, 20);
        
        btnReg.addActionListener((ActionEvent arg0) -> {
            try {
                if(!getUsernameReg().isEmpty() & !getPasswordReg().isEmpty()){
                    String query = "INSERT INTO `users`(`username`, `password`) VALUES ('" + getUsernameReg() + "','" + getPasswordReg() + "')";
                    
                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(query);
                    
                    JOptionPane.showMessageDialog(null,"Berhasil Mendaftarkan User");
                }else{
                    JOptionPane.showMessageDialog(null,"Username dan Password Tidak Boleh Kosong");
                }
            } catch (HeadlessException | SQLException ex){
                if(ex.getMessage().contains("Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"Username Sudah Digunakan");
                }
            }
        });

        btnLogin.addActionListener((ActionEvent arg0) -> {
            try {
                String query = "SELECT `username`, `password` FROM `users` WHERE `username` = '" + getUsernameLogin() + "'";
                
                connector.statement = connector.koneksi.createStatement();
                ResultSet resultSet = connector.statement.executeQuery(query);
                
                if(resultSet.next()){
                    if(resultSet.getString("password").equals(getPasswordLogin())){
                        JOptionPane.showMessageDialog(null,"Berhasil Login");
                    }else{
                        JOptionPane.showMessageDialog(null,"Password Salah");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Username Tidak Ditemukan");
                }
            } catch (HeadlessException | SQLException ex){
                System.out.println(ex.getMessage());
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
 
    public String getUsernameReg(){
        return tfUsernameReg.getText();
    }

    public String getPasswordReg() {
        return tfPasswordReg.getText();
    }
    public String getUsernameLogin(){
        return tfUsernameLogin.getText();
    }

    public String getPasswordLogin() {
        return tfPasswordLogin.getText();
    }

}
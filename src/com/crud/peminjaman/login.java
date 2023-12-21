package com.crud.peminjaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;

    public login() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(new JLabel());
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Username = txtUsername.getText();
                char[] Password = txtPassword.getPassword();

                // Perform login check by querying the database
                if (authenticateUser(Username, Password)) {
                    JOptionPane.showMessageDialog(null, "Login berhasil!");
                    dispose();
                    // Open the main application window (AplikasiLaundry)
                    menu UI = new menu();
                    JPanel root = UI.getMainPanel();

                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.setPreferredSize(new Dimension(600,300));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Login gagal. Coba lagi.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    // Method to authenticate user against a database
    private boolean authenticateUser(String Username, char[] Password) {
        String jdbcUrl = "jdbc:mysql://localhost/perpustakaan";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM login WHERE Username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, new String(Password));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error authenticating user", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login loginForm = new login();
                loginForm.setVisible(true);
            }
        });
    }
}
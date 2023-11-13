package com.crud.peminjaman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buku {

    public Buku() {
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
        tambahBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul, penulis, penerbit, tahun_terbit;
                judul = jTextFieldJudul.getText();
                penulis = jTextFieldPenulis.getText();
                penerbit = jTextFieldPenerbit.getText();
                tahun_terbit = jTexteFieldTahun.getText();

                try{
                    preparedStatement = Connector.ConnectDB().prepareStatement("INSERT INTO buku (judul, penulis, penerbit, tahun_terbit) values (?,?,?,?);");
                    preparedStatement.setString(1, judul);
                    preparedStatement.setString(2, penulis);
                    preparedStatement.setString(3, penerbit);
                    preparedStatement.setString(4, tahun_terbit);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null, "data berhasil ditambahkan");
                } catch (SQLException err){
                    Logger.getLogger(DataInterface.class.getName()).log(Level.SEVERE, null, err);
                }
                jTextFieldJudul.setText("");
                jTextFieldPenulis.setText("");
                jTextFieldPenerbit.setText("");
                jTexteFieldTahun.setText("");
            }
        });
        hapusBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SwingUtilities.invokeLater(Buku::createHapusbukuGUI);}
        });
    }

    private void showData() {
        try {
            Object[] columnTitle = {"Judul", "Penulis", "Penerbit", "Tanggal terbit"};
            tableModel = new DefaultTableModel(null, columnTitle);
            jTablebuku.setModel(tableModel);

            Connection connection = Connector.ConnectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            resultSet = statement.executeQuery("SELECT * FROM buku");
            while (resultSet.next()) {
                Object[] data = {
                        resultSet.getString("judul"),
                        resultSet.getString("penulis"),
                        resultSet.getString("penerbit"),
                        resultSet.getString("tahun_terbit")
                };
                tableModel.addRow(data);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public JPanel getMainBukuPanel() {
        showData();
        return mainBukuPanel;
    }

    private static void createHapusbukuGUI() {
        HapusbukuPanel hapusbukuUI = new HapusbukuPanel();
        JPanel hapusRoot = hapusbukuUI.getHapusBukuPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(hapusRoot);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private JTable jTablebuku;
    private JPanel mainBukuPanel;
    private JPanel bukuPanel;
    private JTextField jTextFieldJudul;
    private JTextField jTextFieldPenulis;
    private JTextField jTextFieldPenerbit;
    private JTextField jTexteFieldTahun;
    private JButton kembaliButton;
    private JButton hapusBukuButton;
    private JButton tambahBukuButton;

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;



}


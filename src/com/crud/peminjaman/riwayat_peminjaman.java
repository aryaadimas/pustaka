package com.crud.peminjaman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class riwayat_peminjaman {

    public riwayat_peminjaman() {
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getMainRiwayatPanel(){
        showData();
        return MainRiwayatPanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try {
            Object[] columnTittle = {"id", "nama", "buku", "tanggal_meminjam", "tenggat"};
            tableModel = new DefaultTableModel(null, columnTittle);
            table1.setModel(tableModel);

            Connection connection = Connector.ConnectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            resultSet = statement.executeQuery("SELECT * FROM riwayat_peminjaman");
            while (resultSet.next()){
                Object[] data = {
                        resultSet.getString("id"),
                        resultSet.getString("nama"),
                        resultSet.getString("buku"),
                        resultSet.getString("tanggal_meminjam"),
                        resultSet.getString("tenggat")
                };
                tableModel.addRow(data);
            }
        } catch (SQLException err){
            throw new RuntimeException(err);
        }
    }
    private JTable table1;
    private JPanel MainRiwayatPanel;
    private JButton kembaliButton;
}

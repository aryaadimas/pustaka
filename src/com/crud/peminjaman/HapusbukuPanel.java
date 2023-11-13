package com.crud.peminjaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;


public class HapusbukuPanel {
    public HapusbukuPanel() {
        HapusbukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul;
                judul = jTextFieldjudul.getText();
                if (!Objects.equals(judul,"")) {
                    try {
                        preparedStatement = Connector.ConnectDB().prepareStatement("DELETE FROM buku WHERE judul=?;");
                        preparedStatement.setString(1, judul);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Buku berhasil dihapus");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "judul tidak boleh kosong");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getHapusBukuPanel(){
        return hapusBukuPanel;
    }

    public PreparedStatement preparedStatement;
    private JPanel hapusBukuPanel;
    private JTextField jTextFieldjudul;
    private JButton backButton;
    private JButton HapusbukuButton;
}
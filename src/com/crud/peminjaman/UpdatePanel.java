package com.crud.peminjaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePanel {

    public UpdatePanel() {
        perbaruiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idpeminjam, nama, buku, tanggal, tenggat;
                idpeminjam = jidd.getText();
                nama = jNama.getText();
                buku = jBuku.getText();
                tanggal = jTanggal.getText();
                tenggat = jTenggat.getText();
                if (!Objects.equals(idpeminjam, "") && !Objects.equals(nama, "") && !Objects.equals(buku,"") && !Objects.equals(tanggal,"") && !Objects.equals(tenggat,"")){
                    try {
                        preparedStatement = Connector.ConnectDB().prepareStatement("UPDATE peminjaman SET nama=?, buku=?, tanggal_meminjam=?, tenggat=? WHERE id=?;");
                        preparedStatement.setString(1, nama);
                        preparedStatement.setString(2, buku);
                        preparedStatement.setString(3, tanggal);
                        preparedStatement.setString(4, tenggat);
                        preparedStatement.setString(5, idpeminjam);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "berhasil memperbarui data");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException exception){
                        exception.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "data tidak boleh kosong");
                }

            }
        });
        batalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getUpdatePanel(){
        return mainUpdatePanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel mainUpdatePanel;
    private JLabel jTitleUpdatePanel;
    private JTextField jidd;
    private JTextField jNama;
    private JTextField jBuku;
    private JTextField jTanggal;
    private JButton batalButton;
    private JButton perbaruiButton;
    private JTextField jTenggat;
}

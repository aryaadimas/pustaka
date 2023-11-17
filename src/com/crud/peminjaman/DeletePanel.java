package com.crud.peminjaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeletePanel {
    public DeletePanel() {
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idpeminjam;
                idpeminjam = jTextFieldid.getText();
                if (!Objects.equals(idpeminjam, "")) {
                    try {
                        preparedStatement = Connector.ConnectDB().prepareStatement("DELETE FROM peminjaman WHERE  id=?;");
                        preparedStatement.setString(1, idpeminjam);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "id ini tidak boleh kosong");
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

    public JPanel getDeletePanel(){
        return deletePanel;
    }


    public PreparedStatement preparedStatement;
    private JPanel deletePanel;
    private JLabel jTitleDeletePanel;
    private JPanel jPanelID;
    private JPanel jPanelButton;
    private JTextField jTextFieldid;
    private JButton batalButton;
    private JButton hapusButton;
    private JLabel jidDelete;
    private JLabel jidLabel;
}

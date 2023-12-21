package com.crud.peminjaman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataInterface {

    public DataInterface() {
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama, buku, tanggal, tenggat;
                nama = jTextFieldNama.getText();
                buku = jTextFieldBuku.getText();
                tanggal = jTextFieldTanggal.getText();
                tenggat = jTextFieldTenggat.getText();

                try{
                    preparedStatement = Connector.ConnectDB().prepareStatement("INSERT INTO peminjaman (nama, buku, tanggal_meminjam, tenggat) values (?,?,?,?);");
                    preparedStatement.setString(1, nama);
                    preparedStatement.setString(2, buku);
                    preparedStatement.setString(3, tanggal);
                    preparedStatement.setString(4, tenggat);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                } catch (SQLException err){
                    Logger.getLogger(DataInterface.class.getName()).log(Level.SEVERE, null, err);
                }
                jTextFieldNama.setText("");
                jTextFieldBuku.setText("");
                jTextFieldTanggal.setText("");
                jTextFieldTenggat.setText("");
            }
        });
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SwingUtilities.invokeLater(DataInterface::createUpdateGUI);
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(DataInterface::createDeleteGUI);
            }
        });


        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getMainPanel(){
        showData();
        return mainPanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try {
            Object[] columnTitle = {"id peminjaman", "Nama", "Buku", "tanggal meminjam", "tenggat"};
            tableModel = new DefaultTableModel(null, columnTitle);
            jTable.setModel(tableModel);

            Connection connection = Connector.ConnectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            resultSet = statement.executeQuery("SELECT * FROM peminjaman");
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

    private static void createUpdateGUI(){
        UpdatePanel updateUI = new UpdatePanel();
        JPanel updateRoot = updateUI.getUpdatePanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(updateRoot);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createDeleteGUI(){
        DeletePanel deleteUI = new DeletePanel();
        JPanel deleteRoot = deleteUI.getDeletePanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(deleteRoot);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




    private JPanel mainPanel;
    private JLabel JTitlePanel;
    private JTextField jTextFieldNama;
    private JTextField jTextFieldBuku;
    private JTextField jTextFieldTanggal;
    private JTextField jTextFieldTenggat;
    private JTable jTable;
    private JButton AddButton;
    private JButton UpdateButton;
    private JButton DeleteButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JPanel jThirdPanel;
    private JLabel jLabelNama;
    private JLabel jLabelBuku;
    private JLabel jLabelTanggal;
    private JLabel jLabelTenggat;
    private JButton kembaliButton;
    private JTextField JtextSearch;


    public static void createBukuGUI()
    {

            Buku bukuUI = new Buku();
            JPanel bukuRoot = bukuUI.getMainPanel();

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(bukuRoot);
            frame.setPreferredSize(new Dimension(450,600));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

    }

    public static void createriwayatGUI() {
        riwayat_peminjaman riwayatUI = new riwayat_peminjaman();
        JPanel riwayatRoot = riwayatUI.getMainRiwayatPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(riwayatRoot);
        frame.setPreferredSize(new Dimension(530,380));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void createGUI(){
        DataInterface UI = new DataInterface();
        JPanel root = UI.getMainPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.setPreferredSize(new Dimension(600,700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

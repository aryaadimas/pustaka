package com.crud.peminjaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu {
    private JPanel panelll;
    private JButton RIWAYATButton;
    private JButton PEMINJAMANButton;
    private JButton DATABUKUButton;

    public menu() {
        DATABUKUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SwingUtilities.invokeLater(DataInterface::createBukuGUI);

            }
        });

        RIWAYATButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SwingUtilities.invokeLater(DataInterface::createriwayatGUI);

            }
        });
        PEMINJAMANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SwingUtilities.invokeLater(DataInterface::createGUI);

            }
        });
    }





    public void setVisible(boolean b) {
    }

    public JPanel getMainPanel() {
        showData();
        return panelll;
    }

    private void showData() {
    }
}

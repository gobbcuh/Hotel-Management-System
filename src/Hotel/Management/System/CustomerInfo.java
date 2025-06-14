package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;

public class CustomerInfo extends JFrame {
    CustomerInfo() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 40, 900, 450);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        setLayout(null);
        setSize(900, 600);
        setLocation(500, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}

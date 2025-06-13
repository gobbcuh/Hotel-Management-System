package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;

public class NewCustomer extends JFrame {
    NewCustomer() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 500);
        panel.setLayout(null);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        setLayout(null);
        setLocation(500, 150);
        setSize(850, 550);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}

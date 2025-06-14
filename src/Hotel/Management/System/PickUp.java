package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;

public class PickUp extends JFrame {
    PickUp() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 790, 590);
        panel.setLayout(null);
        add(panel);

        JLabel PUS = new JLabel("Pick Up Service");
        PUS.setBounds(90, 11, 89, 14);
        PUS.setBackground(Color.WHITE);
        PUS.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(PUS);


        setLayout(null);
        setSize(800, 600);
        setLocation(500, 100);
        setVisible(true);
    }
    public static void main(String[] args) {
        new PickUp();
    }
}

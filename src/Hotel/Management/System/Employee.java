package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;

public class Employee extends JFrame {
    Employee() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        setLayout(null);
        setLocation(430, 100);
        setSize(1000, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }
}

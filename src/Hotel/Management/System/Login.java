package Hotel.Management.System;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Login extends JFrame {
    Login() {
        JLabel label1 = new JLabel("Username");
        label1.setBounds(40, 20, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20)); // change font
        label1.setForeground(Color.WHITE);
        add(label1);

        getContentPane().setBackground(new Color(3, 45, 48)); // change background color
        setLayout(null);
        setLocation(400, 40);
        setSize(450, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

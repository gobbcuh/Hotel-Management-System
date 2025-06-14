package Hotel.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1, b2;

    Login() {
        JLabel label1 = new JLabel("Username");
        label1.setBounds(40, 20, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        add(label1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(40, 70, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        add(label2);

        textField1 = new JTextField();
        textField1.setBounds(150, 20, 150, 30);
        textField1.setForeground(Color.WHITE);
        textField1.setBackground(new Color(26, 104, 110));
        add(textField1);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(150, 70, 150, 30);
        passwordField1.setForeground(Color.WHITE);
        passwordField1.setBackground(new Color(26, 104, 110));
        add(passwordField1);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(666, 375, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(150, 120, 666, 375);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(40, 300, 120, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 300, 120, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        JLabel registerLabel = new JLabel("<html>Don't have an account? <a href='' style='text-decoration:none;color:white;'>Register</a></html>");
        registerLabel.setBounds(40, 340, 260, 30);
        registerLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(registerLabel);

        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Register();
                setVisible(false);
            }
        });

        getContentPane().setBackground(new Color(3, 45, 48));
        setLayout(null);
        setLocation(400, 40);
        setSize(450, 600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                con c = new con();
                String user = textField1.getText();
                String pass = new String(passwordField1.getPassword());

                String q = "select * from login where username = '"+user+"' and password = '"+pass+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()) {
                    new Dashboard();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == b2) {
            System.exit(102);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
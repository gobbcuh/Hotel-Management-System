package Hotel.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Register extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1, passwordField2;
    JButton b1, b2;

    Register() {
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

        JLabel label3 = new JLabel("Confirm Password");
        label3.setBounds(40, 120, 150, 30);
        label3.setFont(new Font("Tahoma", Font.BOLD, 16));
        label3.setForeground(Color.WHITE);
        add(label3);

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

        passwordField2 = new JPasswordField();
        passwordField2.setBounds(150, 120, 150, 30);
        passwordField2.setForeground(Color.WHITE);
        passwordField2.setBackground(new Color(26, 104, 110));
        add(passwordField2);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(666, 375, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(150, 170, 666, 375);
        add(label);

        b1 = new JButton("Register");
        b1.setBounds(40, 350, 120, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 350, 120, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        JLabel loginLabel = new JLabel("<html>Already have an account? <a href='' style='text-decoration:none;color:white;'>Log in</a></html>");
        loginLabel.setBounds(40, 390, 260, 30);
        loginLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(loginLabel);

        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Login();
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
                String user = textField1.getText();
                String pass1 = new String(passwordField1.getPassword());
                String pass2 = new String(passwordField2.getPassword());

                if (user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required");
                    return;
                }

                if (!pass1.equals(pass2)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                    return;
                }

                con c = new con();
                String checkQuery = "select * from login where username = '" + user + "'";
                ResultSet resultSet = c.statement.executeQuery(checkQuery);
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists");
                    return;
                }

                String insertQuery = "insert into login (username, password) values ('" + user + "', '" + pass1 + "')";
                c.statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Registration successful! Please log in.");
                new Login();
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Registration failed");
            }

        } else if (e.getSource() == b2) {
            System.exit(102);
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}
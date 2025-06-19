package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login2 extends JFrame implements ActionListener {
    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1, b2;

    Login2() {
        JLabel label1 = new JLabel("Username");
        label1.setBounds(90, 180, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
        add(label1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(90, 230, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.BLACK);
        add(label2);

        textField1 = new JTextField();
        textField1.setBounds(200, 180, 150, 30);
        textField1.setForeground(Color.BLACK);
        textField1.setBackground(Color.WHITE);
        add(textField1);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(200, 230, 150, 30);
        passwordField1.setForeground(Color.BLACK);
        passwordField1.setBackground(Color.WHITE);
        add(passwordField1);

        b1 = new JButton("Login");
        b1.setBounds(90, 380, 120, 30);
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(230, 380, 120, 30);
        b2.setFont(new Font("Tahoma", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/admin-login.png"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 440, 580);
        add(label);

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
                String pass = passwordField1.getText();

                String q = "select * from login2 where username = ? and password = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, user);
                pstmt.setString(2, pass);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    Session.setAdmin(true);
                    if (Session.isCalledFromUpdateRoom()) {
                        new UpdateRoom();
                        Session.setCalledFromUpdateRoom(false);
                    } else {
                        new admin();
                    }
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid");
                }
                pstmt.close();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            new Dashboard();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login2();
    }
}
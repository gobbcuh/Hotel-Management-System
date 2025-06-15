package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame {
    UpdateRoom() {
        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(350, 205, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 100, 350, 205);
        panel.add(label);

        JLabel label1 = new JLabel("Update Room Status");
        label1.setBounds(124,11,222,25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JLabel label2 = new JLabel("ID :");
        label2.setBounds(25,88,46,14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        Choice c = new Choice();
        c.setBounds(248,85,140,20);
        panel.add(c);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select * from customer");
            while (resultSet.next()){
                c.add(resultSet.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading customer data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25,129,120,14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248,129,140,20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Availability :");
        label4.setBounds(25,174,97,14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248,174,140,20);
        panel.add(textField4);

        JLabel label5 = new JLabel("Clean Status :");
        label5.setBounds(25,216,97,14);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.BLACK);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248,216,140,20);
        panel.add(textField5);

        JButton update = new JButton("Update");
        update.setBounds(120,300,89,23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con C = new con();
                    String status = textField5.getText();
                    String availability = textField4.getText();
                    String roomNumber = textField3.getText();

                    String query = "update room set cleaning_status = ?, availability = ? where room_number = ?";
                    PreparedStatement pstmt = C.connection.prepareStatement(query);
                    pstmt.setString(1, status);
                    pstmt.setString(2, availability);
                    pstmt.setString(3, roomNumber);
                    pstmt.executeUpdate();
                    pstmt.close();

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating room: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(180,345,89,23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(60,345,89,23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                String q = "select * from customer where number = ?";
                try {
                    con C = new con();
                    PreparedStatement pstmt = C.connection.prepareStatement(q);
                    pstmt.setString(1, id);
                    ResultSet resultSet = pstmt.executeQuery();

                    while (resultSet.next()) {
                        textField3.setText(resultSet.getString("room"));
                    }
                    pstmt.close();

                    String q2 = "select * from room where room_number = ?";
                    PreparedStatement pstmt2 = C.connection.prepareStatement(q2);
                    pstmt2.setString(1, textField3.getText());
                    ResultSet resultSet1 = pstmt2.executeQuery();

                    while (resultSet1.next()) {
                        textField4.setText(resultSet1.getString("availability"));
                        textField5.setText(resultSet1.getString("cleaning_status"));
                    }
                    pstmt2.close();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error checking room: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(950,450);
        setLocation(305,135);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
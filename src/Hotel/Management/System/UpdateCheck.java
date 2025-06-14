package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame {
    UpdateCheck() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Check-In Details");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("ID :");
        label2.setBounds(25, 88, 46, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice c = new Choice();
        c.setBounds(248, 85, 140, 20);
        panel.add(c);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                c.add(resultSet.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 107, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248, 129, 140, 20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Name :");
        label4.setBounds(25, 174, 97, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248, 174, 140, 20);
        panel.add(textField4);

        JLabel label5 = new JLabel("Checked-In :");
        label5.setBounds(25, 216, 97, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248, 216, 140, 20);
        panel.add(textField5);

        JLabel label6 = new JLabel("Amount Paid :");
        label6.setBounds(25, 261, 150, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textField6 = new JTextField();
        textField6.setBounds(248, 261, 140, 20);
        panel.add(textField6);

        JLabel label7 = new JLabel("Pending Amount :");
        label7.setBounds(25, 302, 150, 20);
        label7.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label7.setForeground(Color.WHITE);
        panel.add(label7);

        JTextField textField7 = new JTextField();
        textField7.setBounds(248, 302, 140, 20);
        panel.add(textField7);

        JButton update = new JButton("Update");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                String deposit = textField6.getText();
                String roomNumber = textField3.getText();
                String name = textField4.getText();

                try {
                    if (id == null || deposit == null || deposit.trim().isEmpty() ||
                            roomNumber == null || roomNumber.trim().isEmpty() ||
                            name == null || name.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a customer and fill all required fields.");
                        return;
                    }

                    int depositAmount;
                    try {
                        depositAmount = Integer.parseInt(deposit);
                        if (depositAmount < 0) {
                            JOptionPane.showMessageDialog(null, "Deposit amount cannot be negative.");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric deposit amount.");
                        return;
                    }

                    con conn = new con();
                    // Verify if name matches ID document
                    String idCheckQuery = "SELECT name FROM customer WHERE number = ?";
                    PreparedStatement idStmt = conn.connection.prepareStatement(idCheckQuery);
                    idStmt.setString(1, id);
                    ResultSet idRs = idStmt.executeQuery();
                    if (idRs.next()) {
                        String originalName = idRs.getString("name");
                        if (!name.equalsIgnoreCase(originalName)) {
                            JOptionPane.showMessageDialog(null, "Name must match the ID document.");
                            return;
                        }
                    }

                    // Check if room is available (not occupied by another customer)
                    String roomCheckQuery = "SELECT * FROM customer WHERE room = ? AND number != ?";
                    PreparedStatement roomStmt = conn.connection.prepareStatement(roomCheckQuery);
                    roomStmt.setString(1, roomNumber);
                    roomStmt.setString(2, id);
                    ResultSet roomRs = roomStmt.executeQuery();
                    if (roomRs.next()) {
                        JOptionPane.showMessageDialog(null, "Room is already occupied by another customer.");
                        return;
                    }

                    // Check if room exists and get price
                    String roomQuery = "SELECT price FROM room WHERE room_number = ?";
                    PreparedStatement roomPriceStmt = conn.connection.prepareStatement(roomQuery);
                    roomPriceStmt.setString(1, roomNumber);
                    ResultSet roomResult = roomPriceStmt.executeQuery();
                    if (!roomResult.next()) {
                        JOptionPane.showMessageDialog(null, "Room not found.");
                        return;
                    }
                    String price = roomResult.getString("price");
                    int pendingAmount = Integer.parseInt(price) - depositAmount;
                    textField7.setText(String.valueOf(pendingAmount));

                    // Update customer details
                    String updateQuery = "UPDATE customer SET room = ?, name = ?, deposit = ? WHERE number = ?";
                    PreparedStatement pstmt = conn.connection.prepareStatement(updateQuery);
                    pstmt.setString(1, roomNumber);
                    pstmt.setString(2, name);
                    pstmt.setString(3, deposit);
                    pstmt.setString(4, id);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Updated Successfully");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating details: " + ex.getMessage());
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
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
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                String q = "select * from customer where number = ?";
                try {
                    con c = new con();
                    PreparedStatement pstmt = c.connection.prepareStatement(q);
                    pstmt.setString(1, id);
                    ResultSet resultSet = pstmt.executeQuery();

                    if (resultSet.next()) {
                        textField3.setText(resultSet.getString("room"));
                        textField4.setText(resultSet.getString("name"));
                        textField5.setText(resultSet.getString("checkintime"));
                        textField6.setText(resultSet.getString("deposit"));
                    }

                    ResultSet resultSet1 = c.statement.executeQuery("select * from room where room_number = '" + textField3.getText() + "'");
                    if (resultSet1.next()) {
                        String price = resultSet1.getString("price");
                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(textField6.getText());
                        textField7.setText("" + amountPaid);
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        setLayout(null);
        setSize(950, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
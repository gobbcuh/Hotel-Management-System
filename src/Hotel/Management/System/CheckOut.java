package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class CheckOut extends JFrame {
    CheckOut() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(295, 170, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel labelimg = new JLabel(imageIcon1);
        labelimg.setBounds(440, 80, 295, 170);
        panel.add(labelimg);

        JLabel label = new JLabel("Check Out");
        label.setBounds(100, 20, 150, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        panel.add(label);

        JLabel userID = new JLabel("Customer ID");
        userID.setBounds(30, 80, 150, 30);
        userID.setFont(new Font("Tahoma", Font.BOLD, 14));
        userID.setForeground(Color.BLACK);
        panel.add(userID);

        Choice customer = new Choice();
        customer.setBounds(200, 80, 150, 25);
        panel.add(customer);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30, 130, 150, 30);
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomNum.setForeground(Color.BLACK);
        panel.add(roomNum);

        JLabel labelRoomNumber = new JLabel();
        labelRoomNumber.setBounds(200, 130, 150, 30);
        labelRoomNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelRoomNumber.setForeground(Color.BLACK);
        panel.add(labelRoomNumber);

        JLabel checkinTime = new JLabel("Check In Time");
        checkinTime.setBounds(30, 180, 150, 30);
        checkinTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkinTime.setForeground(Color.BLACK);
        panel.add(checkinTime);

        JLabel labelCheckinTime = new JLabel();
        labelCheckinTime.setBounds(200, 180, 250, 30);
        labelCheckinTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelCheckinTime.setForeground(Color.BLACK);
        panel.add(labelCheckinTime);

        JLabel checkoutTime = new JLabel("Check Out Time");
        checkoutTime.setBounds(30, 230, 150, 30);
        checkoutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkoutTime.setForeground(Color.BLACK);
        panel.add(checkoutTime);

        Date date = new Date();
        JLabel labelCheckoutTime = new JLabel("" + date);
        labelCheckoutTime.setBounds(200, 230, 250, 30);
        labelCheckoutTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelCheckoutTime.setForeground(Color.BLACK);
        panel.add(labelCheckoutTime);

        try {
            con c = new con();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                customer.add(resultSet.getString("number"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading customer data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton checkOut = new JButton("Check Out");
        checkOut.setBounds(30, 300, 120, 30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = customer.getSelectedItem();
                String roomNumber = labelRoomNumber.getText();

                if (customerId == null || roomNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a customer and check room details", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    con c = new con();

                    String verifyQuery = "SELECT * FROM customer WHERE number = ?";
                    PreparedStatement verifyStmt = c.connection.prepareStatement(verifyQuery);
                    verifyStmt.setString(1, customerId);
                    ResultSet rs = verifyStmt.executeQuery();
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
                        verifyStmt.close();
                        return;
                    }
                    verifyStmt.close();

                    String deleteQuery = "DELETE FROM customer WHERE number = ?";
                    PreparedStatement deleteStmt = c.connection.prepareStatement(deleteQuery);
                    deleteStmt.setString(1, customerId);
                    deleteStmt.executeUpdate();
                    deleteStmt.close();

                    String updateRoomQuery = "UPDATE room SET availability = 'Available' WHERE room_number = ?";
                    PreparedStatement updateStmt = c.connection.prepareStatement(updateRoomQuery);
                    updateStmt.setString(1, roomNumber);
                    int rowsAffected = updateStmt.executeUpdate();
                    updateStmt.close();

                    if (rowsAffected == 0) {
                        JOptionPane.showMessageDialog(null, "Room number not found", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Check-Out Completed Successfully");
                    setVisible(false);

                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error during check-out: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(300, 300, 120, 30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = customer.getSelectedItem();
                if (customerId == null) {
                    JOptionPane.showMessageDialog(null, "Please select a customer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    con c = new con();
                    String query = "SELECT * FROM customer WHERE number = ?";
                    PreparedStatement pstmt = c.connection.prepareStatement(query);
                    pstmt.setString(1, customerId);
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                        labelRoomNumber.setText(resultSet.getString("room"));
                        labelCheckinTime.setText(resultSet.getString("checkintime"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    pstmt.close();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error checking customer: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(165, 300, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(800, 400);
        setLocation(385, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
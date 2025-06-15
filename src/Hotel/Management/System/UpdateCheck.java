package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame {
    Choice c;
    JTextField textField3, textField4, textField5, textField6, textField7, textPrice;
    JComboBox durationComboBox;

    UpdateCheck() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(320, 185, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(520, 140, 320, 185);
        panel.add(label);

        JLabel label1 = new JLabel("Check-In Details");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JLabel label2 = new JLabel("ID :");
        label2.setBounds(25, 88, 46, 14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        c = new Choice();
        c.setBounds(248, 85, 160, 20);
        panel.add(c);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                c.add(resultSet.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading customer data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 120, 14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        textField3 = new JTextField();
        textField3.setBounds(248, 129, 160, 20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Name :");
        label4.setBounds(25, 174, 97, 14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

        textField4 = new JTextField();
        textField4.setBounds(248, 174, 160, 20);
        panel.add(textField4);

        JLabel label5 = new JLabel("Checked-in :");
        label5.setBounds(25, 216, 97, 14);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.BLACK);
        panel.add(label5);

        textField5 = new JTextField();
        textField5.setBounds(248, 216, 160, 20);
        panel.add(textField5);

        JLabel labelDuration = new JLabel("Duration :");
        labelDuration.setBounds(25, 261, 150, 14);
        labelDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDuration.setForeground(Color.BLACK);
        panel.add(labelDuration);

        durationComboBox = new JComboBox(new String[] {"3 Hours", "6 Hours", "12 Hours", "24 Hours"});
        durationComboBox.setBounds(248, 261, 160, 20);
        durationComboBox.setBackground(Color.WHITE);
        durationComboBox.setForeground(Color.BLACK);
        durationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(durationComboBox);

        JLabel labelPrice = new JLabel("Price :");
        labelPrice.setBounds(25, 302, 150, 14);
        labelPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelPrice.setForeground(Color.BLACK);
        panel.add(labelPrice);

        textPrice = new JTextField();
        textPrice.setBounds(248, 302, 160, 20);
        textPrice.setEditable(false);
        panel.add(textPrice);

        JLabel label6 = new JLabel("Amount Paid :");
        label6.setBounds(25, 343, 150, 14);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        label6.setForeground(Color.BLACK);
        panel.add(label6);

        textField6 = new JTextField();
        textField6.setBounds(248, 343, 160, 20);
        panel.add(textField6);

        JLabel label7 = new JLabel("Pending Amount :");
        label7.setBounds(25, 384, 150, 14);
        label7.setFont(new Font("Tahoma", Font.BOLD, 14));
        label7.setForeground(Color.BLACK);
        panel.add(label7);

        textField7 = new JTextField();
        textField7.setBounds(248, 384, 160, 20);
        textField7.setEditable(false);
        panel.add(textField7);

        ItemListener priceUpdater = e -> {
            String room = textField3.getText();
            String duration = (String) durationComboBox.getSelectedItem();
            String deposit = textField6.getText();
            String price = "";
            if (room != null && !room.isEmpty() && duration != null) {
                String roomType = "";
                try {
                    int roomNum = Integer.parseInt(room);
                    if (roomNum >= 101 && roomNum <= 105) {
                        roomType = "Proton Room";
                    } else if (roomNum >= 201 && roomNum <= 205) {
                        roomType = "Neutron Room";
                    } else if (roomNum >= 301 && roomNum <= 305) {
                        roomType = "Electron Suite";
                    }
                } catch (NumberFormatException ex) {
                }
                switch (roomType) {
                    case "Proton Room":
                    case "Neutron Room":
                        switch (duration) {
                            case "3 Hours": price = "1600.00"; break;
                            case "6 Hours": price = "2400.00"; break;
                            case "12 Hours": price = "3600.00"; break;
                            case "24 Hours": price = "4800.00"; break;
                        }
                        break;
                    case "Electron Suite":
                        switch (duration) {
                            case "3 Hours": price = "2200.00"; break;
                            case "6 Hours": price = "3200.00"; break;
                            case "12 Hours": price = "5000.00"; break;
                            case "24 Hours": price = "6800.00"; break;
                        }
                        break;
                }
            }
            textPrice.setText(price);
            try {
                double priceValue = price.isEmpty() ? 0.0 : Double.parseDouble(price);
                double depositValue = deposit.isEmpty() ? 0.0 : Double.parseDouble(deposit);
                double pending = priceValue - depositValue;
                textField7.setText(String.format("%.2f", pending));
            } catch (NumberFormatException ex) {
                textField7.setText("");
            }
        };
        durationComboBox.addItemListener(priceUpdater);
        textField3.addActionListener(e -> priceUpdater.itemStateChanged(null));
        textField6.addActionListener(e -> priceUpdater.itemStateChanged(null));

        JButton update = new JButton("Update");
        update.setBounds(56, 420, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con C = new con();
                    String id = c.getSelectedItem();
                    String room = textField3.getText();
                    String name = textField4.getText();
                    String checkin = textField5.getText();
                    String deposit = textField6.getText();
                    String duration = (String) durationComboBox.getSelectedItem();
                    String price = textPrice.getText();

                    if (room.isEmpty() || name.isEmpty() || checkin.isEmpty() || deposit.isEmpty() || duration == null || price.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String q = "update customer set room = ?, name = ?, checkintime = ?, deposit = ?, duration = ?, price = ? where number = ?";
                    PreparedStatement pstmt = C.connection.prepareStatement(q);
                    pstmt.setString(1, room);
                    pstmt.setString(2, name);
                    pstmt.setString(3, checkin);
                    pstmt.setString(4, deposit);
                    pstmt.setString(5, duration);
                    pstmt.setString(6, price);
                    pstmt.setString(7, id);
                    pstmt.executeUpdate();
                    pstmt.close();

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating customer: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(168, 420, 89, 23);
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
        check.setBounds(281, 420, 89, 23);
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
                        durationComboBox.setSelectedItem(resultSet.getString("duration"));
                        textPrice.setText(resultSet.getString("price"));
                    }
                    pstmt.close();

                    // Trigger priceUpdater to calculate pending amount
                    priceUpdater.itemStateChanged(null);
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error checking customer: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(950, 500);
        setLocation(303, 110);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
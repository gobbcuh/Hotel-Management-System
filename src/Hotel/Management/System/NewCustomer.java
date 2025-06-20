package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class NewCustomer extends JFrame implements ActionListener {
    JComboBox comboBox, durationComboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposit, textPrice;
    JLabel checkintime;
    Choice c1;
    JRadioButton r1, r2;
    JButton add, back;
    JDateChooser checkInDateChooser;
    JCheckBox reserveCheckBox;

    NewCustomer() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 650);
        panel.setLayout(null);
        panel.setBackground(new Color(250, 213, 213));
        add(panel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/customer.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(550, 120, 200, 100);
        panel.add(l1);

        JLabel labelName = new JLabel("NEW CUSTOMER FORM");
        labelName.setBounds(300, 10, 250, 25);
        labelName.setForeground(Color.BLACK);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelName);

        JLabel labelID = new JLabel("ID:");
        labelID.setBounds(35, 60, 200, 14);
        labelID.setForeground(Color.BLACK);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelID);

        comboBox = new JComboBox(new String[]{"PhilSys ID", "Driver's License", "SSS ID", "Pag-Ibig ID", "TIN ID"});
        comboBox.setBounds(271, 60, 150, 20);
        comboBox.setBackground(new Color(255, 255, 255));
        panel.add(comboBox);

        JLabel labelNumber = new JLabel("Number:");
        labelNumber.setBounds(35, 100, 200, 14);
        labelNumber.setForeground(Color.BLACK);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 100, 150, 20);
        panel.add(textFieldNumber);

        JLabel labelCustomerName = new JLabel("Name:");
        labelCustomerName.setBounds(35, 140, 200, 14);
        labelCustomerName.setForeground(Color.BLACK);
        labelCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCustomerName);

        TextName = new JTextField();
        TextName.setBounds(271, 140, 150, 20);
        panel.add(TextName);

        JLabel labelGender = new JLabel("Sex:");
        labelGender.setBounds(35, 180, 200, 14);
        labelGender.setForeground(Color.BLACK);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(271, 180, 70, 20);
        r1.setBackground(new Color(250, 213, 213));
        r1.setForeground(Color.BLACK);
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(350, 180, 80, 20);
        r2.setBackground(new Color(250, 213, 213));
        r2.setForeground(Color.BLACK);
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel labelCountry = new JLabel("Country:");
        labelCountry.setBounds(35, 220, 200, 14);
        labelCountry.setForeground(Color.BLACK);
        labelCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCountry);

        TextCountry = new JTextField();
        TextCountry.setBounds(271, 220, 150, 20);
        panel.add(TextCountry);

        JLabel labelRoom = new JLabel("Room:");
        labelRoom.setBounds(35, 260, 200, 14);
        labelRoom.setForeground(Color.BLACK);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            con c = new con();
            ResultSet resultSet = c.statement.executeQuery("select * from room where availability = 'Available'");
            while (resultSet.next()) {
                c1.add(resultSet.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 260, 150, 20);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(c1);

        reserveCheckBox = new JCheckBox("Reserve (Check for future date)");
        reserveCheckBox.setBounds(271, 300, 200, 20);
        reserveCheckBox.setBackground(new Color(250, 213, 213));
        reserveCheckBox.setForeground(Color.BLACK);
        reserveCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(reserveCheckBox);
        reserveCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInDateChooser.setEnabled(reserveCheckBox.isSelected());
                checkInDateChooser.setMinSelectableDate(new Date());
                if (!reserveCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Check-In mode selected. Current date and time will be used for immediate check-in.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    checkintime.setText("" + new Date());
                } else {
                    JOptionPane.showMessageDialog(null, "Reserve mode selected. Please select a future check-in date.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JLabel labelCheckInDate = new JLabel("Check-In Date:");
        labelCheckInDate.setBounds(35, 340, 200, 14);
        labelCheckInDate.setForeground(Color.BLACK);
        labelCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCheckInDate);

        checkInDateChooser = new JDateChooser();
        checkInDateChooser.setBounds(271, 340, 150, 20);
        checkInDateChooser.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        checkInDateChooser.setEnabled(false); // Disabled by default
        panel.add(checkInDateChooser);

        JLabel labelCheckinTime = new JLabel("Check-In Time:");
        labelCheckinTime.setBounds(35, 380, 200, 14);
        labelCheckinTime.setForeground(Color.BLACK);
        labelCheckinTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCheckinTime);

        checkintime = new JLabel("" + new Date());
        checkintime.setBounds(271, 380, 200, 20);
        checkintime.setForeground(Color.BLACK);
        checkintime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(checkintime);

        JLabel labelDeposit = new JLabel("Deposit:");
        labelDeposit.setBounds(35, 420, 200, 14);
        labelDeposit.setForeground(Color.BLACK);
        labelDeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelDeposit);

        TextDeposit = new JTextField();
        TextDeposit.setBounds(271, 420, 150, 20);
        panel.add(TextDeposit);

        JLabel labelDuration = new JLabel("Duration:");
        labelDuration.setBounds(35, 460, 200, 14);
        labelDuration.setForeground(Color.BLACK);
        labelDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelDuration);

        durationComboBox = new JComboBox(new String[]{"3 hours", "6 hours", "12 hours", "24 hours"});
        durationComboBox.setBounds(271, 460, 150, 20);
        durationComboBox.setBackground(new Color(255, 255, 255));
        panel.add(durationComboBox);

        JLabel labelPrice = new JLabel("Price:");
        labelPrice.setBounds(35, 500, 200, 14);
        labelPrice.setForeground(Color.BLACK);
        labelPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelPrice);

        textPrice = new JTextField();
        textPrice.setBounds(271, 500, 150, 20);
        panel.add(textPrice);

        add = new JButton("Add");
        add.setBounds(100, 550, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        panel.add(add);
        add.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(250, 550, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(this);

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == c1 || e.getSource() == durationComboBox) {
                    try {
                        con c = new con();
                        String selectedRoom = c1.getSelectedItem();
                        String selectedDuration = (String) durationComboBox.getSelectedItem();
                        ResultSet resultSet = c.statement.executeQuery("select * from room where room_number = '" + selectedRoom + "'");
                        if (resultSet.next()) {
                            String roomType = resultSet.getString("bed_type");
                            int price = 0;
                            if (roomType.equals("Proton Room") || roomType.equals("Neutron Room")) {
                                switch (selectedDuration) {
                                    case "3 hours":
                                        price = 1600;
                                        break;
                                    case "6 hours":
                                        price = 2400;
                                        break;
                                    case "12 hours":
                                        price = 3600;
                                        break;
                                    case "24 hours":
                                        price = 4800;
                                        break;
                                }
                            } else if (roomType.equals("Electron Suite")) {
                                switch (selectedDuration) {
                                    case "3 hours":
                                        price = 2200;
                                        break;
                                    case "6 hours":
                                        price = 3200;
                                        break;
                                    case "12 hours":
                                        price = 5000;
                                        break;
                                    case "24 hours":
                                        price = 6800;
                                        break;
                                }
                            }
                            textPrice.setText(String.valueOf(price));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        c1.addItemListener(itemListener);
        durationComboBox.addItemListener(itemListener);

        setUndecorated(true);
        setLayout(null);
        setSize(850, 700);
        setLocation(320, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String document = (String) comboBox.getSelectedItem();
            String number = textFieldNumber.getText();
            String name = TextName.getText();
            String country = TextCountry.getText();
            String deposit = TextDeposit.getText();
            String duration = (String) durationComboBox.getSelectedItem();
            String price = textPrice.getText();
            String room = c1.getSelectedItem();
            String gender = null;
            if (r1.isSelected()) {
                gender = "Male";
            } else if (r2.isSelected()) {
                gender = "Female";
            }

            if (deposit.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Deposit cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double depositAmount;
            try {
                depositAmount = Double.parseDouble(deposit);
                if (depositAmount < 0) {
                    JOptionPane.showMessageDialog(null, "Deposit cannot be negative", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Deposit must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateStr = sdf.format(currentDate);
            String checkInDateStr;

            if (reserveCheckBox.isSelected()) {
                Date checkInDate = checkInDateChooser.getDate();
                if (checkInDate == null) {
                    JOptionPane.showMessageDialog(null, "Please select a future check-in date for reservation", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                checkInDateStr = sdf.format(checkInDate);
                if (checkInDateStr.substring(0, 10).equals(currentDateStr.substring(0, 10))) {
                    JOptionPane.showMessageDialog(null, "Reservations must be for a future date. For today, uncheck Reserve and proceed with Check-In.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                checkInDateStr = currentDateStr;
            }

            try {
                con c = new con();

                String checkRoomQuery = "SELECT availability FROM room WHERE room_number = ?";
                PreparedStatement checkStmt = c.connection.prepareStatement(checkRoomQuery);
                checkStmt.setString(1, room);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && !rs.getString("availability").equals("Available")) {
                    JOptionPane.showMessageDialog(null, "Room is already occupied or reserved", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                checkStmt.close();

                String checkReservationQuery = "SELECT * FROM reservations WHERE room_number = ? AND checkin_date LIKE ? AND status = 'Pending'";
                PreparedStatement checkResStmt = c.connection.prepareStatement(checkReservationQuery);
                checkResStmt.setString(1, room);
                checkResStmt.setString(2, checkInDateStr.substring(0, 10) + "%");
                ResultSet resRs = checkResStmt.executeQuery();
                if (resRs.next()) {
                    JOptionPane.showMessageDialog(null, "Room is already reserved for this date", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                checkResStmt.close();

                if (!reserveCheckBox.isSelected()) {
                    String customerQuery = "INSERT INTO customer (document, number, name, gender, country, room, checkintime, deposit, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = c.connection.prepareStatement(customerQuery);
                    pstmt.setString(1, document);
                    pstmt.setString(2, number);
                    pstmt.setString(3, name);
                    pstmt.setString(4, gender);
                    pstmt.setString(5, country);
                    pstmt.setString(6, room);
                    pstmt.setString(7, checkInDateStr);
                    pstmt.setString(8, deposit);
                    pstmt.setString(9, duration);
                    pstmt.setString(10, price);
                    pstmt.executeUpdate();
                    pstmt.close();

                    String updateRoomQuery = "UPDATE room SET availability = 'Occupied' WHERE room_number = ?";
                    PreparedStatement updateStmt = c.connection.prepareStatement(updateRoomQuery);
                    updateStmt.setString(1, room);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    JOptionPane.showMessageDialog(null, "Customer Checked In Successfully");
                } else {
                    String reservationQuery = "INSERT INTO reservations (document, number, name, gender, country, room_number, checkin_date, duration, price, deposit, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pending')";
                    PreparedStatement pstmt = c.connection.prepareStatement(reservationQuery);
                    pstmt.setString(1, document);
                    pstmt.setString(2, number);
                    pstmt.setString(3, name);
                    pstmt.setString(4, gender);
                    pstmt.setString(5, country);
                    pstmt.setString(6, room);
                    pstmt.setString(7, checkInDateStr);
                    pstmt.setString(8, duration);
                    pstmt.setString(9, price);
                    pstmt.setString(10, deposit);
                    pstmt.executeUpdate();
                    pstmt.close();

                    String updateRoomQuery = "UPDATE room SET availability = 'Reserved' WHERE room_number = ?";
                    PreparedStatement updateStmt = c.connection.prepareStatement(updateRoomQuery);
                    updateStmt.setString(1, room);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    JOptionPane.showMessageDialog(null, "Reservation Made Successfully for " + checkInDateStr);
                }

                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding customer: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
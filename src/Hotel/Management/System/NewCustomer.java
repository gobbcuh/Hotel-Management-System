package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class NewCustomer extends JFrame implements ActionListener {
    JComboBox comboBox, durationComboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposit, textPrice;
    JRadioButton r1, r2;
    Choice c1;
    JLabel date;
    JButton add, back;

    NewCustomer() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setLayout(null);
        panel.setBackground(new Color(250, 213, 213));
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(295, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imglabel = new JLabel(imageIcon1);
        imglabel.setBounds(490, 140, 295, 150);
        panel.add(imglabel);

        JLabel labelName = new JLabel("New Customer Form");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.BLACK);
        panel.add(labelName);

        JLabel labelID = new JLabel("ID :");
        labelID.setBounds(35, 76, 200, 14);
        labelID.setForeground(Color.BLACK);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelID);

        comboBox = new JComboBox(new String[] {"Passport", "PhilSys ID", "Voter's ID", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(comboBox);

        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setForeground(Color.BLACK);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelNumber);
        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        JLabel labelname = new JLabel("Name :");
        labelname.setBounds(35, 151, 200, 14);
        labelname.setForeground(Color.BLACK);
        labelname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelname);
        TextName = new JTextField();
        TextName.setBounds(271, 151, 150, 20);
        panel.add(TextName);

        JLabel labelGender = new JLabel("Sex :");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setForeground(Color.BLACK);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r1.setForeground(Color.BLACK);
        r1.setBackground(new Color(250, 213, 213));
        r1.setBounds(271, 191, 80, 12);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r2.setForeground(Color.BLACK);
        r2.setBackground(new Color(250, 213, 213));
        r2.setBounds(350, 191, 80, 12);
        panel.add(r2);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        JLabel labelCountry = new JLabel("Country :");
        labelCountry.setBounds(35, 231, 200, 14);
        labelCountry.setForeground(Color.BLACK);
        labelCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCountry);
        TextCountry = new JTextField();
        TextCountry.setBounds(271, 231, 150, 20);
        panel.add(TextCountry);

        JLabel labelRoom = new JLabel("Room Number :");
        labelRoom.setBounds(35, 271, 200, 14);
        labelRoom.setForeground(Color.BLACK);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            con c = new con();
            ResultSet resultSet = c.statement.executeQuery("select * from room where availability = 'Available'");
            while(resultSet.next()) {
                c1.add(resultSet.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading rooms: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        c1.setBounds(271, 271, 150, 20);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setForeground(Color.BLACK);
        c1.setBackground(Color.WHITE);
        panel.add(c1);

        JLabel labelDuration = new JLabel("Duration :");
        labelDuration.setBounds(35, 311, 200, 14);
        labelDuration.setForeground(Color.BLACK);
        labelDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelDuration);

        durationComboBox = new JComboBox(new String[] {"3 Hours", "6 Hours", "12 Hours", "24 Hours"});
        durationComboBox.setBounds(271, 311, 150, 20);
        durationComboBox.setBackground(Color.WHITE);
        durationComboBox.setForeground(Color.BLACK);
        durationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(durationComboBox);

        JLabel labelPrice = new JLabel("Price :");
        labelPrice.setBounds(35, 351, 200, 14);
        labelPrice.setForeground(Color.BLACK);
        labelPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelPrice);

        textPrice = new JTextField();
        textPrice.setBounds(271, 351, 150, 20);
        textPrice.setEditable(false);
        textPrice.setBackground(Color.WHITE);
        textPrice.setForeground(Color.BLACK);
        textPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(textPrice);

        JLabel labelCIS = new JLabel("Checked-In :");
        labelCIS.setBounds(35, 391, 200, 14);
        labelCIS.setForeground(Color.BLACK);
        labelCIS.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCIS);

        Date date1 = new Date();
        date = new JLabel(date1.toString());
        date.setBounds(271, 391, 200, 14);
        date.setForeground(Color.BLACK);
        date.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(date);

        JLabel labelDeposit = new JLabel("Deposit :");
        labelDeposit.setBounds(35, 431, 200, 14);
        labelDeposit.setForeground(Color.BLACK);
        labelDeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelDeposit);
        TextDeposit = new JTextField();
        TextDeposit.setBounds(271, 431, 150, 20);
        panel.add(TextDeposit);

        add = new JButton("Add");
        add.setBounds(100, 470, 120, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(260, 470, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        panel.add(back);

        // Update price based on Room Number and Duration
        ItemListener priceUpdater = e -> {
            String room = c1.getSelectedItem();
            String duration = (String) durationComboBox.getSelectedItem();
            String price = "";
            if (room != null && duration != null) {
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
                    // Handle invalid room number
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
        };
        c1.addItemListener(priceUpdater);
        durationComboBox.addItemListener(priceUpdater);

        setUndecorated(true);
        setLayout(null);
        setLocation(355, 80);
        setSize(850, 550);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }

            if (radioBTN == null) {
                JOptionPane.showMessageDialog(null, "Please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = TextName.getText();
            String s4 = radioBTN;
            String s5 = TextCountry.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = TextDeposit.getText();
            String s9 = (String) durationComboBox.getSelectedItem();
            String s10 = textPrice.getText();

            if (s2.isEmpty() || s3.isEmpty() || s5.isEmpty() || s6 == null || s8.isEmpty() || s9 == null || s10.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                con c = new con();
                String q = "insert into customer values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, s1);
                pstmt.setString(2, s2);
                pstmt.setString(3, s3);
                pstmt.setString(4, s4);
                pstmt.setString(5, s5);
                pstmt.setString(6, s6);
                pstmt.setString(7, s7);
                pstmt.setString(8, s8);
                pstmt.setString(9, s9);
                pstmt.setString(10, s10);
                pstmt.executeUpdate();

                String q1 = "update room set availability = 'Occupied' where room_number = ?";
                PreparedStatement pstmt2 = c.connection.prepareStatement(q1);
                pstmt2.setString(1, s6);
                pstmt2.executeUpdate();

                pstmt.close();
                pstmt2.close();

                JOptionPane.showMessageDialog(null, "Added Successfully");
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
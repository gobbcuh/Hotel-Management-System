package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CheckOut extends JFrame {
    CheckOut() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check Out");
        label.setBounds(100, 20, 150, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel UserID = new JLabel("Customer ID");
        UserID.setBounds(30, 80, 150, 30);
        UserID.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserID.setForeground(Color.WHITE);
        panel.add(UserID);

        Choice Customer = new Choice();
        Customer.setBounds(200, 80, 150, 25);
        panel.add(Customer);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30, 130, 150, 30);
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomNum.setForeground(Color.WHITE);
        panel.add(roomNum);

        JLabel labelRoomNumber = new JLabel();
        labelRoomNumber.setBounds(200, 130, 150, 30);
        labelRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoomNumber.setForeground(Color.WHITE);
        panel.add(labelRoomNumber);

        JLabel checkinTime = new JLabel("Check In Time");
        checkinTime.setBounds(30, 180, 150, 30);
        checkinTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkinTime.setForeground(Color.WHITE);
        panel.add(checkinTime);

        JLabel labelCheckinTime = new JLabel();
        labelCheckinTime.setBounds(30, 180, 150, 30);
        labelCheckinTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelCheckinTime.setForeground(Color.WHITE);
        panel.add(labelCheckinTime);

        JLabel checkoutTime = new JLabel("Check Out Time");
        checkoutTime.setBounds(30, 230, 150, 30);
        checkoutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkoutTime.setForeground(Color.WHITE);
        panel.add(checkoutTime);

        Date date = new Date();

        JLabel LabelCheckoutTime = new JLabel(""+date);
        LabelCheckoutTime.setBounds(200, 230, 250, 30);
        LabelCheckoutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        LabelCheckoutTime.setForeground(Color.WHITE);
        panel.add(LabelCheckoutTime);


        setLayout(null);
        setSize(800, 400);
        setLocation(500, 210);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}

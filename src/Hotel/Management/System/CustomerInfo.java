package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame {
    CustomerInfo() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1090, 690);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        JLabel checkedInLabel = new JLabel("Checked In Customers");
        checkedInLabel.setBounds(10, 10, 200, 20);
        checkedInLabel.setForeground(Color.BLACK);
        checkedInLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(checkedInLabel);

        JTable checkedInTable = new JTable();
        checkedInTable.setBounds(10, 70, 1070, 200);
        checkedInTable.setBackground(new Color(250, 213, 213));
        checkedInTable.setForeground(Color.BLACK);
        panel.add(checkedInTable);

        try {
            con c = new con();
            String q = "SELECT document AS ID, number AS Number, name AS Name, gender AS Sex, country AS Country, " +
                    "room AS Room, checkintime AS 'Check-In Time', deposit AS Deposit, duration AS Duration, " +
                    "price AS Price, 'Checked In' AS Status FROM customer";
            ResultSet resultSet = c.statement.executeQuery(q);
            checkedInTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel reservedLabel = new JLabel("Reserved Customers");
        reservedLabel.setBounds(10, 290, 200, 20);
        reservedLabel.setForeground(Color.BLACK);
        reservedLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(reservedLabel);

        JTable reservedTable = new JTable();
        reservedTable.setBounds(10, 350, 1070, 200);
        reservedTable.setBackground(new Color(250, 213, 213));
        reservedTable.setForeground(Color.BLACK);
        panel.add(reservedTable);

        try {
            con c = new con();
            String q = "SELECT document AS ID, number AS Number, name AS Name, gender AS Sex, country AS Country, " +
                    "room_number AS Room, checkin_date AS 'Check-In Time', deposit AS Deposit, duration AS Duration, " +
                    "price AS Price, 'Reserved' AS Status FROM reservations WHERE status = 'Pending'";
            ResultSet resultSet = c.statement.executeQuery(q);
            reservedTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] headers = {"ID", "Number", "Name", "Sex", "Country", "Room", "Check-In Time", "Deposit", "Duration", "Price", "Status"};
        int[] xPositions = {41, 138, 255, 375, 485, 595, 670, 810, 910, 1015, 1115};
        for (int i = 0; i < headers.length; i++) {
            JLabel header = new JLabel(headers[i]);
            header.setBounds(xPositions[i], 40, 100, 16);
            header.setForeground(Color.BLACK);
            header.setFont(new Font("Tahoma", Font.BOLD, 14));
            panel.add(header);

            JLabel header2 = new JLabel(headers[i]);
            header2.setBounds(xPositions[i], 320, 100, 16);
            header2.setForeground(Color.BLACK);
            header2.setFont(new Font("Tahoma", Font.BOLD, 14));
            panel.add(header2);
        }

        JButton back = new JButton("Back");
        back.setBounds(370, 600, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(1100, 700);
        setLocation(176, 50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}
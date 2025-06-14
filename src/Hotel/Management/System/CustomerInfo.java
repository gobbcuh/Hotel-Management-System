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
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 40, 900, 450);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            con c = new con();
            String q = "select * from customer";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel id = new JLabel("ID");
        id.setBounds(31, 11, 100, 14);
        id.setForeground(Color.BLACK);
        id.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(id);

        JLabel number = new JLabel("Number");
        number.setBounds(150, 11, 100, 14);
        number.setForeground(Color.BLACK);
        number.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(number);

        JLabel name = new JLabel("Name");
        name.setBounds(270, 11, 100, 14);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(name);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(360, 11, 100, 14);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(gender);

        JLabel country = new JLabel("Country");
        country.setBounds(480, 11, 100, 14);
        country.setForeground(Color.BLACK);
        country.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(country);

        JLabel room = new JLabel("Room");
        room.setBounds(600, 11, 100, 14);
        room.setForeground(Color.BLACK);
        room.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(room);

        JLabel Time = new JLabel("Check-In Time");
        Time.setBounds(680, 11, 110, 14);
        Time.setForeground(Color.BLACK);
        Time.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Time);

        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(800, 11, 100, 14);
        deposit.setForeground(Color.BLACK);
        deposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(deposit);

        JButton back = new JButton("Back");
        back.setBounds(370, 510, 120, 30);
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
        setSize(900, 600);
        setLocation(330, 50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}

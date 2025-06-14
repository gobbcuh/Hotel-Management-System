package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class PickUp extends JFrame {
    PickUp() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 213, 213));
        panel.setBounds(5, 5, 790, 590);
        panel.setLayout(null);
        add(panel);

        JLabel PUS = new JLabel("Pick Up Service");
        PUS.setBounds(300, 40, 160, 25);
        PUS.setForeground(Color.BLACK);
        PUS.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(PUS);

        JLabel TOC = new JLabel("Type of Car");
        TOC.setBounds(32, 97, 89, 17);
        TOC.setForeground(Color.BLACK);
        TOC.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(TOC);

        Choice c = new Choice();
        c.setBounds(123, 94, 150, 25);
        panel.add(c);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select * from driver");
            while (resultSet.next()) {
                c.add(resultSet.getString("car_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable();
        table.setBounds(10, 233, 800, 250);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            con C = new con();
            String q = "select * from driver";
            ResultSet resultSet = C.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(24, 208, 46, 14);
        name.setForeground(Color.BLACK);
        panel.add(name);

        JLabel age = new JLabel("Age");
        age.setBounds(165, 208, 46, 14);
        age.setForeground(Color.BLACK);
        panel.add(age);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(264, 208, 46, 14);
        gender.setForeground(Color.BLACK);
        panel.add(gender);

        JLabel company = new JLabel("Company");
        company.setBounds(366, 208, 100, 14);
        company.setForeground(Color.BLACK);
        panel.add(company);

        JLabel carName = new JLabel("Car Name");
        carName.setBounds(486, 208, 100, 14);
        carName.setForeground(Color.BLACK);
        panel.add(carName);

        JLabel available = new JLabel("Available");
        available.setBounds(600, 208, 100, 14);
        available.setForeground(Color.BLACK);
        panel.add(available);

        JLabel location = new JLabel("Location");
        location.setBounds(700, 208, 100, 14);
        location.setForeground(Color.BLACK);
        panel.add(location);

        JButton display = new JButton("Display");
        display.setBounds(200, 500, 120, 30);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        panel.add(display);
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from driver where car_name = '"+c.getSelectedItem()+"'";
                try {
                    con c = new con();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(420, 500, 120, 30);
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
        setSize(800, 600);
        setLocation(400, 55);
        setVisible(true);
    }
    public static void main(String[] args) {
        new PickUp();
    }
}

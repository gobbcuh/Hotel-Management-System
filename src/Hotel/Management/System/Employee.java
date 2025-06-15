package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Employee extends JFrame {
    Employee() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        JLabel title = new JLabel("Customer Reference");
        title.setBounds(350, 20, 300, 25);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        panel.add(title);

        JTable table = new JTable();
        table.setBounds(10, 80, 980, 424);
        table.setForeground(Color.BLACK);
        table.setBackground(new Color(250, 213, 213));
        panel.add(table);

        try {
            con c = new con();
            String EmployeeSQL = "select name, gender, job, phone, email from employee";
            ResultSet resultSet = c.statement.executeQuery(EmployeeSQL);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton back = new JButton("Back");
        back.setBounds(400, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JLabel name = new JLabel("Name");
        name.setBounds(75, 60, 70, 19);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(name);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(285, 60, 70, 19);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(gender);

        JLabel job = new JLabel("Role");
        job.setBounds(475, 60, 70, 19);
        job.setForeground(Color.BLACK);
        job.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(job);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(665, 60, 70, 19);
        phone.setForeground(Color.BLACK);
        phone.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(phone);

        JLabel email = new JLabel("Email");
        email.setBounds(865, 60, 70, 19);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(email);

        setUndecorated(true);
        setLayout(null);
        setLocation(279, 55);
        setSize(1000, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }
}
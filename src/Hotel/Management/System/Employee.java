package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee extends JFrame {
    Employee() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 34, 980, 450);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(3, 45, 48));
        panel.add(table);

        try {
            con c = new con();
            String EmployeeSQL = "select * from employee";
            ResultSet resultSet = c.statement.executeQuery(EmployeeSQL);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton back = new JButton("Back");
        back.setBounds(350, 500, 120, 30);
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
        name.setBounds(41, 11, 70, 19);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(name);

        JLabel Age = new JLabel("Age");
        Age.setBounds(159, 11, 70, 19);
        Age.setForeground(Color.WHITE);
        Age.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Age);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(273, 11, 70, 19);
        gender.setForeground(Color.WHITE);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(gender);

        JLabel job = new JLabel("Job");
        job.setBounds(416, 11, 70, 19);
        job.setForeground(Color.WHITE);
        job.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(job);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(536, 11, 70, 19);
        salary.setForeground(Color.WHITE);
        salary.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(salary);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(656, 11, 70, 19);
        phone.setForeground(Color.WHITE);
        phone.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(phone);

        JLabel email = new JLabel("Email");
        email.setBounds(786, 11, 70, 19);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(email);

        JLabel natID = new JLabel("PhilSys No.");
        natID.setBounds(896, 11, 70, 19);
        natID.setForeground(Color.WHITE);
        natID.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(natID);

        setLayout(null);
        setLocation(430, 100);
        setSize(1000, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }
}

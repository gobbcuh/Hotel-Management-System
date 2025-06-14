package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class PickUp extends JFrame {
    PickUp() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 790, 590);
        panel.setLayout(null);
        add(panel);

        JLabel PUS = new JLabel("Pick Up Service");
        PUS.setBounds(90, 11, 160, 25);
        PUS.setForeground(Color.WHITE);
        PUS.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(PUS);

        JLabel TOC = new JLabel("Type of Car");
        TOC.setBounds(32, 97, 89, 14);
        TOC.setForeground(Color.WHITE);
        TOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try {
            con C = new con();
            String q = "select * from driver";
            ResultSet resultSet = C.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }


        setLayout(null);
        setSize(800, 600);
        setLocation(500, 100);
        setVisible(true);
    }
    public static void main(String[] args) {
        new PickUp();
    }
}

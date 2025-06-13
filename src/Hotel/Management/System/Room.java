package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Room extends JFrame {
    JTable table;

    Room() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png")); // to be uploaded
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 200, 200, 200);
        panel.add(label);

        table = new JTable();
        table.setBounds(10, 40, 500, 400);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try {
            con c = new con();
            String RoomInfo = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(RoomInfo);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        setLocation(500, 100);
        setSize(900, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}

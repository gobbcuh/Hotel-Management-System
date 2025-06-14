package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {
    JTable table;
    JButton back;

    Room() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(295, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(550, 10, 295, 150);
        panel.add(label);

        table = new JTable();
        table.setBounds(20, 40, 500, 400);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            con c = new con();
            String RoomInfo = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(RoomInfo);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200, 500, 120, 30);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });

        JLabel room = new JLabel("Room No.");
        room.setBounds(15, 15, 80, 19);
        room.setForeground(Color.BLACK);
        room.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(room);

        JLabel availability = new JLabel("Availability");
        availability.setBounds(122, 15, 80, 19);
        availability.setForeground(Color.BLACK);
        availability.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(availability);

        JLabel Clean = new JLabel("Clean Status");
        Clean.setBounds(219, 15, 150, 19);
        Clean.setForeground(Color.BLACK);
        Clean.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Clean);

        JLabel Price = new JLabel("Price");
        Price.setBounds(333, 15, 80, 19);
        Price.setForeground(Color.BLACK);
        Price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Price);

        JLabel Bed = new JLabel("Bed Type");
        Bed.setBounds(420, 15, 80, 19);
        Bed.setForeground(Color.BLACK);
        Bed.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Bed);

        setUndecorated(true);
        setLayout(null);
        setLocation(350, 80);
        setSize(900, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}

package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {
    JCheckBox checkBox;
    Choice choice;
    JTable table;

    SearchRoom(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        add(panel);

        JLabel searchForRoom = new JLabel("Search for Room");
        searchForRoom.setBounds(250, 11, 186, 31);
        searchForRoom.setForeground(Color.WHITE);
        searchForRoom.setFont(new Font("Tahoma", Font.BOLD,20));
        panel.add(searchForRoom);

        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400, 69, 205, 23);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(3, 45, 48));
        panel.add(checkBox);

        choice = new Choice();
        choice.add("Single Bed");
        choice.add("Double Bed");
        choice.setBounds(153, 70, 120, 20);
        panel.add(choice);

        table = new JTable();
        table.setBounds(0, 187, 700, 250);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try {
            con c = new con();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }


        setLayout(null);
        setLocation(500, 200);
        setSize(700, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}

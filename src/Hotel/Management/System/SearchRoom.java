package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame implements ActionListener {
    JCheckBox checkBox;
    Choice choice;
    JTable table;
    JButton add, back;

    SearchRoom(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 213, 213));
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        add(panel);

        JLabel searchForRoom = new JLabel("Search for Room");
        searchForRoom.setBounds(250, 11, 186, 31);
        searchForRoom.setForeground(Color.BLACK);
        searchForRoom.setFont(new Font("Tahoma", Font.BOLD,20));
        panel.add(searchForRoom);

        JLabel rbt = new JLabel("Room Bed Type:");
        rbt.setBounds(50, 73, 120, 20);
        rbt.setForeground(Color.BLACK);
        rbt.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(rbt);

        JLabel rn = new JLabel("Room No.");
        rn.setBounds(23, 111, 150, 20);
        rn.setForeground(Color.BLACK);
        rn.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(rn);

        JLabel available = new JLabel("Availability");
        available.setBounds(175, 111, 150, 20);
        available.setForeground(Color.BLACK);
        available.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(458, 111, 150, 20);
        price.setForeground(Color.BLACK);
        price.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(price);

        JLabel BT = new JLabel("Bed Type");
        BT.setBounds(580, 111, 150, 20);
        BT.setForeground(Color.BLACK);
        BT.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(BT);

        JLabel SS = new JLabel("Clean Status");
        SS.setBounds(306, 111, 150, 20);
        SS.setForeground(Color.BLACK);
        SS.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(SS);


        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400, 69, 205, 23);
        checkBox.setForeground(Color.BLACK);
        checkBox.setBackground(new Color(250, 213, 213));
        panel.add(checkBox);

        choice = new Choice();
        choice.add("Proton Room");
        choice.add("Neutron Room");
        choice.add("Electron Suite");
        choice.setBounds(172, 70, 120, 20);
        panel.add(choice);

        table = new JTable();
        table.setBounds(10, 140, 700, 250);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            con c = new con();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        add = new JButton("Search");
        add.setBounds(200, 400, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(380, 400, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        setUndecorated(true);
        setLayout(null);
        setLocation(420, 100);
        setSize(700, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String Q = "select * from room where bed_type = '"+choice.getSelectedItem()+"'";
            String Q1 = "select * from room where availability = 'available' And bed_type = '"+choice.getSelectedItem()+"'";
            try {
                con c = new con();
                ResultSet resultSet = c.statement.executeQuery(Q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

                if (checkBox.isSelected()) {
                    ResultSet resultSet1 = c.statement.executeQuery(Q1);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet1));
                }

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}

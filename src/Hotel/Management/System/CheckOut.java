package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CheckOut extends JFrame {
    CheckOut() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(295, 170, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel labelimg = new JLabel(imageIcon1);
        labelimg.setBounds(440, 80, 295, 170);
        panel.add(labelimg);

        JLabel label = new JLabel("Check Out");
        label.setBounds(100, 20, 150, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        panel.add(label);

        JLabel UserID = new JLabel("Customer ID");
        UserID.setBounds(30, 80, 150, 30);
        UserID.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserID.setForeground(Color.BLACK);
        panel.add(UserID);

        Choice Customer = new Choice();
        Customer.setBounds(200, 80, 150, 25);
        panel.add(Customer);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30, 130, 150, 30);
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomNum.setForeground(Color.BLACK);
        panel.add(roomNum);

        JLabel labelRoomNumber = new JLabel();
        labelRoomNumber.setBounds(200, 130, 150, 30);
        labelRoomNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelRoomNumber.setForeground(Color.BLACK);
        panel.add(labelRoomNumber);

        JLabel checkinTime = new JLabel("Check In Time");
        checkinTime.setBounds(30, 180, 150, 30);
        checkinTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkinTime.setForeground(Color.BLACK);
        panel.add(checkinTime);

        JLabel labelCheckinTime = new JLabel();
        labelCheckinTime.setBounds(200, 180, 250, 30);
        labelCheckinTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelCheckinTime.setForeground(Color.BLACK);
        panel.add(labelCheckinTime);

        JLabel checkoutTime = new JLabel("Check Out Time");
        checkoutTime.setBounds(30, 230, 150, 30);
        checkoutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkoutTime.setForeground(Color.BLACK);
        panel.add(checkoutTime);

        Date date = new Date();

        JLabel LabelCheckoutTime = new JLabel(""+date);
        LabelCheckoutTime.setBounds(200, 230, 250, 30);
        LabelCheckoutTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelCheckoutTime.setForeground(Color.BLACK);
        panel.add(LabelCheckoutTime);

        try {
            con c = new con();
            ResultSet resultSet = c.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                Customer.add(resultSet.getString("number"));
            }

        } catch (Exception E) {
            E.printStackTrace();
        }

        JButton checkOut = new JButton("Check Out");
        checkOut.setBounds(30, 300, 120, 30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con cv = new con();
                    cv.statement.executeUpdate("delete from customer where number = '"+Customer.getSelectedItem()+"'");
                    cv.statement.executeUpdate("update room set availability = 'available' where room_number = '"+labelRoomNumber.getText()+"'");

                    JOptionPane.showMessageDialog(null, "Done");
                    setVisible(false);

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(300, 300, 120, 30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con c = new con();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from customer where number = '"+Customer.getSelectedItem()+"'");
                    while(resultSet.next()) {
                        labelRoomNumber.setText(resultSet.getString("room"));
                        labelCheckinTime.setText(resultSet.getString("checkintime"));

                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(165, 300, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(800, 400);
        setLocation(385, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}

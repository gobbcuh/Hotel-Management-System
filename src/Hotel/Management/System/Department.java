package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {
    Department() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 213, 213));
        panel.setBounds(5, 5, 992, 590);
        panel.setLayout(null);
        add(panel);

        JButton back = new JButton("Back");
        back.setBounds(45, 530, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.gif"));
        Image image = imageIcon.getImage().getScaledInstance(982, 580, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(0, 0, 982, 580);
        panel.add(label);

        setUndecorated(true);
        setLayout(null);
        setLocation(275, 50);
        setSize(1002, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class addDriver extends JFrame implements ActionListener {
    JTextField nameText, ageText, carCText, carNText, locText;
    JComboBox comboBox, comboBox1;
    JButton add, back;

    addDriver() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 490);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Add Drivers");
        label.setBounds(180, 20, 200, 22);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(label);

        JLabel name = new JLabel("Name");
        name.setBounds(64, 70, 102, 22);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        name.setForeground(Color.BLACK);
        panel.add(name);
        nameText = new JTextField();
        nameText.setBounds(174, 70, 156, 20);
        nameText.setForeground(Color.BLACK);
        nameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameText.setBackground(Color.WHITE);
        panel.add(nameText);

        JLabel age = new JLabel("Age");
        age.setBounds(64, 110, 102, 22);
        age.setFont(new Font("Tahoma", Font.BOLD, 14));
        age.setForeground(Color.BLACK);
        panel.add(age);
        ageText = new JTextField();
        ageText.setBounds(174, 110, 156, 20);
        ageText.setForeground(Color.BLACK);
        ageText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ageText.setBackground(Color.WHITE);
        panel.add(ageText);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(64, 150, 102, 22);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        gender.setForeground(Color.BLACK);
        panel.add(gender);
        comboBox = new JComboBox(new String[] {"Male", "Female"});
        comboBox.setBounds(176, 150, 154, 20);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        panel.add(comboBox);

        JLabel carC = new JLabel("Car Company");
        carC.setBounds(64, 190, 110, 22);
        carC.setFont(new Font("Tahoma", Font.BOLD, 14));
        carC.setForeground(Color.BLACK);
        panel.add(carC);
        carCText = new JTextField();
        carCText.setBounds(174, 190, 156, 20);
        carCText.setForeground(Color.BLACK);
        carCText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        carCText.setBackground(Color.WHITE);
        panel.add(carCText);

        JLabel carN = new JLabel("Car Name");
        carN.setBounds(64, 230, 102, 22);
        carN.setFont(new Font("Tahoma", Font.BOLD, 14));
        carN.setForeground(Color.BLACK);
        panel.add(carN);
        carNText = new JTextField();
        carNText.setBounds(174, 230, 156, 20);
        carNText.setForeground(Color.BLACK);
        carNText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        carNText.setBackground(Color.WHITE);
        panel.add(carNText);

        JLabel available = new JLabel("Availability");
        available.setBounds(64, 270, 102, 22);
        available.setFont(new Font("Tahoma", Font.BOLD, 14));
        available.setForeground(Color.BLACK);
        panel.add(available);
        comboBox1 = new JComboBox(new String[] {"Available", "Occupied"});
        comboBox1.setBounds(176, 270, 154, 20);
        comboBox1.setForeground(Color.BLACK);
        comboBox1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox1.setBackground(Color.WHITE);
        panel.add(comboBox1);

        JLabel loc = new JLabel("Location");
        loc.setBounds(64, 310, 102, 22);
        loc.setFont(new Font("Tahoma", Font.BOLD, 14));
        loc.setForeground(Color.BLACK);
        panel.add(loc);
        locText = new JTextField();
        locText.setBounds(174, 310, 156, 20);
        locText.setForeground(Color.BLACK);
        locText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        locText.setBackground(Color.WHITE);
        panel.add(locText);

        add = new JButton("Add");
        add.setBounds(64, 380, 111, 33);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(198, 380, 111, 33);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(370, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label1 = new JLabel(imageIcon1);
        label1.setBounds(440, 150, 370, 200);
        panel.add(label1);

        setUndecorated(true);
        setLocation(350, 120);
        setLayout(null);
        setSize(900, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = nameText.getText().trim();
            String age = ageText.getText().trim();
            String gender = (String) comboBox.getSelectedItem();
            String company = carCText.getText().trim();
            String carName = carNText.getText().trim();
            String available = (String) comboBox1.getSelectedItem();
            String location = locText.getText().trim();

            if (name.isEmpty() || age.isEmpty() || company.isEmpty() || carName.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                con c = new con();
                String q = "insert into driver values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, name);
                pstmt.setString(2, age);
                pstmt.setString(3, gender);
                pstmt.setString(4, company);
                pstmt.setString(5, carName);
                pstmt.setString(6, available);
                pstmt.setString(7, location);
                pstmt.executeUpdate();
                pstmt.close();

                JOptionPane.showMessageDialog(null, "Driver Added");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding driver: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new addDriver();
    }
}
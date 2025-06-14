package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class NewCustomer extends JFrame implements ActionListener {
    JComboBox comboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposit;
    JRadioButton r1, r2;
    Choice c1;
    JLabel date;
    JButton add, back;

    NewCustomer() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setLayout(null);
        panel.setBackground(new Color(250, 213, 213));
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(295, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imglabel = new JLabel(imageIcon1);
        imglabel.setBounds(490, 140, 295, 150);
        panel.add(imglabel);

        JLabel labelName = new JLabel("New Customer Form");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.BLACK);
        panel.add(labelName);

        JLabel labelID = new JLabel("ID :");
        labelID.setBounds(35, 76, 200, 14);
        labelID.setForeground(Color.BLACK);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelID);

        comboBox = new JComboBox(new String[] {"Passport", "PhilSys ID", "Voter's ID", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(comboBox);

        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setForeground(Color.BLACK);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelNumber);
        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        JLabel labelname = new JLabel("Name :");
        labelname.setBounds(35, 151, 200, 14);
        labelname.setForeground(Color.BLACK);
        labelname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelname);
        TextName = new JTextField();
        TextName.setBounds(271, 151, 150, 20);
        panel.add(TextName);

        JLabel labelGender = new JLabel("Sex :");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setForeground(Color.BLACK);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r1.setForeground(Color.BLACK);
        r1.setBackground(new Color(250, 213, 213));
        r1.setBounds(271, 191, 80, 12);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r2.setForeground(Color.BLACK);
        r2.setBackground(new Color(250, 213, 213));
        r2.setBounds(350, 191, 80, 12);
        panel.add(r2);

        JLabel labelCountry = new JLabel("Country :");
        labelCountry.setBounds(35, 231, 200, 14);
        labelCountry.setForeground(Color.BLACK);
        labelCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCountry);
        TextCountry = new JTextField();
        TextCountry.setBounds(271, 231, 150, 20);
        panel.add(TextCountry);

        JLabel labelRoom = new JLabel("Room Number :");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setForeground(Color.BLACK);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            con c = new con();
            ResultSet resultSet = c.statement.executeQuery("select * from room");
            while(resultSet.next()) {
                c1.add(resultSet.getString("room_number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setForeground(Color.BLACK);
        c1.setBackground(Color.WHITE);
        panel.add(c1);

        JLabel labelCIS = new JLabel("Checked-In :");
        labelCIS.setBounds(35, 316, 200, 14);
        labelCIS.setForeground(Color.BLACK);
        labelCIS.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelCIS);

        Date date1 = new Date();

        date = new JLabel(date1.toString());
        date.setBounds(271, 316, 200, 14);
        date.setForeground(Color.BLACK);
        date.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(date);

        JLabel labelDeposit = new JLabel("Deposit :");
        labelDeposit.setBounds(35, 359, 200, 14);
        labelDeposit.setForeground(Color.BLACK);
        labelDeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelDeposit);
        TextDeposit = new JTextField();
        TextDeposit.setBounds(271, 359, 150, 20);
        panel.add(TextDeposit);

        add = new JButton("Add");
        add.setBounds(100, 430, 120, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(260, 430, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        panel.add(back);

        setUndecorated(true);
        setLayout(null);
        setLocation(355, 80);
        setSize(850, 550);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            con c = new con();
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }

            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = TextName.getText();
            String s4 = radioBTN;
            String s5 = TextCountry.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = TextDeposit.getText();

            try {
                String q = "insert into customer values('"+s1+"', '"+s2+"', '"+s3+"', '"+s4+"', '"+s5+"', '"+s6+"', '"+s7+"', '"+s8+"')";
                String q1 = "update room set availability = 'Occupied' where room_number = "+s6;
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}

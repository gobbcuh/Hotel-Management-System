package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField nameText, ageText, salaryText, phoneText, natIDText, emailText;
    JRadioButton radioButtonM, radioButtonF;
    JComboBox comboBox;
    JButton add, back;

    AddEmployee() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(250, 213, 213));
        add(panel);

        JLabel name = new JLabel("Name");
        name.setBounds(60, 30, 150, 27);
        name.setFont(new Font("Tahoma", Font.BOLD, 17));
        name.setForeground(Color.BLACK);
        panel.add(name);
        nameText = new JTextField();
        nameText.setBounds(210, 30, 150, 27);
        nameText.setBackground(Color.WHITE);
        nameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameText.setForeground(Color.BLACK);
        panel.add(nameText);

        JLabel Age = new JLabel("Age");
        Age.setBounds(60, 80, 150, 27);
        Age.setFont(new Font("Tahoma", Font.BOLD, 17));
        Age.setForeground(Color.BLACK);
        panel.add(Age);
        ageText = new JTextField();
        ageText.setBounds(210, 80, 150, 27);
        ageText.setBackground(Color.WHITE);
        ageText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ageText.setForeground(Color.BLACK);
        panel.add(ageText);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(60, 120, 150, 27);
        gender.setFont(new Font("Tahoma", Font.BOLD, 17));
        gender.setForeground(Color.BLACK);
        panel.add(gender);

        radioButtonM = new JRadioButton("Male");
        radioButtonM.setBounds(210, 120, 70, 27);
        radioButtonM.setBackground(new Color(250, 213, 213));
        radioButtonM.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButtonM.setForeground(Color.BLACK);
        panel.add(radioButtonM);

        radioButtonF = new JRadioButton("Female");
        radioButtonF.setBounds(290, 120, 100, 27);
        radioButtonF.setBackground(new Color(250, 213, 213));
        radioButtonF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButtonF.setForeground(Color.BLACK);
        panel.add(radioButtonF);

        JLabel job = new JLabel("Job");
        job.setBounds(60, 170, 150, 27);
        job.setFont(new Font("Tahoma", Font.BOLD, 17));
        job.setForeground(Color.BLACK);
        panel.add(job);

        comboBox = new JComboBox(new String[] {"Front Desk", "Housekeeping", "Kitchen Staff", "Room Service", "Manager", "Accountant", "Chef"});
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(210, 170, 150, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setForeground(Color.BLACK);
        panel.add(comboBox);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(60, 220, 150, 27);
        salary.setFont(new Font("Tahoma", Font.BOLD, 17));
        salary.setForeground(Color.BLACK);
        panel.add(salary);
        salaryText = new JTextField();
        salaryText.setBounds(210, 220, 150, 27);
        salaryText.setBackground(Color.WHITE);
        salaryText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        salaryText.setForeground(Color.BLACK);
        panel.add(salaryText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(60, 270, 150, 27);
        phone.setFont(new Font("Tahoma", Font.BOLD, 17));
        phone.setForeground(Color.BLACK);
        panel.add(phone);
        phoneText = new JTextField();
        phoneText.setBounds(210, 270, 150, 27);
        phoneText.setBackground(Color.WHITE);
        phoneText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phoneText.setForeground(Color.BLACK);
        panel.add(phoneText);

        JLabel natID = new JLabel("PhilSys Number");
        natID.setBounds(60, 320, 150, 27);
        natID.setFont(new Font("Tahoma", Font.BOLD, 17));
        natID.setForeground(Color.BLACK);
        panel.add(natID);
        natIDText = new JTextField();
        natIDText.setBounds(210, 320, 150, 27);
        natIDText.setBackground(Color.WHITE);
        natIDText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        natIDText.setForeground(Color.BLACK);
        panel.add(natIDText);

        JLabel email = new JLabel("Email");
        email.setBounds(60, 370, 150, 27);
        email.setFont(new Font("Tahoma", Font.BOLD, 17));
        email.setForeground(Color.BLACK);
        panel.add(email);
        emailText = new JTextField();
        emailText.setBounds(210, 370, 150, 27);
        emailText.setBackground(Color.WHITE);
        emailText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailText.setForeground(Color.BLACK);
        panel.add(emailText);

        JLabel AED = new JLabel("Employee Details");
        AED.setBounds(500, 30, 445, 35);
        AED.setFont(new Font("Tahoma", Font.BOLD, 31));
        AED.setForeground(Color.BLACK);
        panel.add(AED);

        add = new JButton("Add");
        add.setBounds(80, 420, 100, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(200, 420, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png")); // to be uploaded
        Image image = imageIcon.getImage().getScaledInstance(370, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(440, 150, 370, 200);
        panel.add(label);

        setUndecorated(true);
        setLocation(350, 120);
        setLayout(null);
        setSize(900, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {
            String name = nameText.getText();
            String age = ageText.getText();
            String salary = salaryText.getText();
            String phone = phoneText.getText();
            String email = emailText.getText();
            String natID = natIDText.getText();
            String job = (String) comboBox.getSelectedItem();
            String gender = null;
            if (radioButtonM.isSelected()) {
                gender = "Male";
            } else if (radioButtonF.isSelected()) {
                gender = "Female";
            }

            try {
                con c = new con();
                String q = "insert into employee values('"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"', '"+natID+"')";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Employee Added");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}

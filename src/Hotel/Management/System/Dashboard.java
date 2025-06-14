package Hotel.Management.System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JButton add, rec;

    Dashboard() {
        super("Covalent Hotel");

        rec = new JButton("Reception");
        rec.setBounds(330, 510, 140, 30);
        rec.setFont(new Font("Tahoma", Font.BOLD, 15));
        rec.setBackground(new Color(250, 213, 213));   // color to be changed
        rec.setForeground(Color.BLACK);
        rec.addActionListener(this);
        add(rec);

        add = new JButton("Admin");
        add.setBounds(823, 510, 140, 30);
        add.setFont(new Font("Tahoma", Font.BOLD, 15));
        add.setBackground(new Color(250, 213, 213));   // color to be changed
        add.setForeground(Color.BLACK);
        add.addActionListener(this);
        add(add);

        /* ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png")); // to be uploaded
        Image i2 = i11.getImage().getScaledInstance(200, 195, Image.SCALE_DEFAULT);
        ImageIcon imageIcon11 = new ImageIcon(i2);
        JLabel label1 = new JLabel(imageIcon11);
        label1.setBounds(850, 300, 200, 195);
        add(label1);

        ImageIcon i111 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png")); // to be uploaded
        Image i22 = i111.getImage().getScaledInstance(200, 195, Image.SCALE_DEFAULT);
        ImageIcon imageIcon111 = new ImageIcon(i22);
        JLabel label11 = new JLabel(imageIcon111);
        label11.setBounds(400, 300, 200, 195);
        add(label11); */

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/dashboard.gif"));
        Image i1 = imageIcon.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(0, -20, 1280, 720);
        add(label);

        setLayout(null);
        setSize(1366, 768);
        setLocation(-8, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rec) {
            new Reception();
            setVisible(false);
        } else {
            new Login2();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}

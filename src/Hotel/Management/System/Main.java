package Hotel.Management.System;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
    Main() {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/main.gif")); // upload gif from canva
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, -80, 1326, 848);
        add(label);

        setLayout(null);
        setLocation(-8, 0);
        setSize(1366, 768);
        setVisible(true);

        try {
            Thread.sleep(5000);
            new Login();
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}

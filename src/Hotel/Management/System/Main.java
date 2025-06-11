package Hotel.Management.System;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
    Main() {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/main.gif")); // upload gif from canva
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 858, 680);
        add(label);

        setLayout(null);
        setLocation(200, 10);
        setSize(858, 680);
        setVisible(true);

        try {
            Thread.sleep(5000);
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}

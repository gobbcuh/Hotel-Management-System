package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;

public class SearchRoom extends JFrame {
    SearchRoom(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        add(panel);

        setLayout(null);
        setLocation(500, 200);
        setSize(700, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}

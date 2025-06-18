package Hotel.Management.System; // TO DO: reserved to checked in when reserved date has ended + reserve can be canceled and updated

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRoom extends JFrame {
    UpdateRoom() {
        if (!Session.isAdmin()) {
            Session.setCalledFromUpdateRoom(true);
            JOptionPane.showMessageDialog(null, "Only admin is allowed to update room status. Please log in as admin.");
            new Login2();
            dispose();
            return;
        }

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/covalent.png"));
        Image image = imageIcon.getImage().getScaledInstance(350, 205, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 100, 350, 205);
        panel.add(label);

        JLabel label1 = new JLabel("Update Room Status");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JLabel label2 = new JLabel("Room Number:");
        label2.setBounds(25, 88, 120, 14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        Choice c = new Choice();
        c.setBounds(248, 85, 140, 20);
        panel.add(c);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select room_number from room");
            while (resultSet.next()) {
                c.add(resultSet.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading room data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel label3 = new JLabel("Availability:");
        label3.setBounds(25, 129, 120, 14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248, 129, 140, 20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Clean Status:");
        label4.setBounds(25, 174, 120, 14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248, 174, 140, 20);
        panel.add(textField4);

        JButton update = new JButton("Update");
        update.setBounds(120, 300, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con C = new con();
                    String status = textField4.getText();
                    String availability = textField3.getText();
                    String roomNumber = c.getSelectedItem();

                    String query = "UPDATE room SET cleaning_status = ?, availability = ? WHERE room_number = ?";
                    PreparedStatement pstmt = C.connection.prepareStatement(query);
                    pstmt.setString(1, status);
                    pstmt.setString(2, availability);
                    pstmt.setString(3, roomNumber);
                    pstmt.executeUpdate();
                    pstmt.close();

                    JOptionPane.showMessageDialog(null, "Room Updated Successfully");
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating room: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(120, 345, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(240, 300, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = c.getSelectedItem();
                try {
                    con C = new con();
                    String q = "SELECT * FROM room WHERE room_number = ?";
                    PreparedStatement pstmt = C.connection.prepareStatement(q);
                    pstmt.setString(1, roomNumber);
                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                        textField3.setText(resultSet.getString("availability"));
                        textField4.setText(resultSet.getString("cleaning_status"));
                    }
                    pstmt.close();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error checking room: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton processReservation = new JButton("Process Reservation");
        processReservation.setBounds(360, 340, 200, 23);
        processReservation.setBackground(Color.BLACK);
        processReservation.setForeground(Color.WHITE);
        panel.add(processReservation);
        processReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = c.getSelectedItem();
                try {
                    con C = new con();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String today = sdf.format(new Date());

                    String reservationQuery = "SELECT * FROM reservations WHERE room_number = ? AND checkin_date LIKE ? AND status = 'Pending'";
                    PreparedStatement resStmt = C.connection.prepareStatement(reservationQuery);
                    resStmt.setString(1, roomNumber);
                    resStmt.setString(2, today + "%");
                    ResultSet resRs = resStmt.executeQuery();
                    if (!resRs.next()) {
                        JOptionPane.showMessageDialog(null, "No reservation found for this room today", "Info", JOptionPane.INFORMATION_MESSAGE);
                        resStmt.close();
                        return;
                    }

                    String customerQuery = "INSERT INTO customer (document, number, name, gender, country, room, checkintime, deposit, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement customerStmt = C.connection.prepareStatement(customerQuery);
                    customerStmt.setString(1, resRs.getString("document"));
                    customerStmt.setString(2, resRs.getString("number"));
                    customerStmt.setString(3, resRs.getString("name"));
                    customerStmt.setString(4, resRs.getString("gender"));
                    customerStmt.setString(5, resRs.getString("country"));
                    customerStmt.setString(6, resRs.getString("room_number"));
                    customerStmt.setString(7, resRs.getString("checkin_date"));
                    customerStmt.setString(8, resRs.getString("deposit"));
                    customerStmt.setString(9, resRs.getString("duration"));
                    customerStmt.setString(10, resRs.getString("price"));
                    customerStmt.executeUpdate();
                    customerStmt.close();

                    String updateResQuery = "UPDATE reservations SET status = 'Confirmed' WHERE room_number = ? AND checkin_date LIKE ?";
                    PreparedStatement updateResStmt = C.connection.prepareStatement(updateResQuery);
                    updateResStmt.setString(1, roomNumber);
                    updateResStmt.setString(2, today + "%");
                    updateResStmt.executeUpdate();
                    updateResStmt.close();

                    String updateRoomQuery = "UPDATE room SET availability = 'Occupied' WHERE room_number = ?";
                    PreparedStatement updateStmt = C.connection.prepareStatement(updateRoomQuery);
                    updateStmt.setString(1, roomNumber);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    JOptionPane.showMessageDialog(null, "Reservation Processed Successfully. Room is now Occupied.");
                    textField3.setText("Occupied");
                    resStmt.close();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error processing reservation: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton logout = new JButton("Log Out");
        logout.setBounds(240, 345, 89, 23);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        panel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutAndRedirectToReception();
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(950, 500);
        setLocation(305, 135);
        setVisible(true);
    }

    private void logoutAndRedirectToReception() {
        Session.setAdmin(false);
        Session.setCalledFromUpdateRoom(false);
        new Reception();
        setVisible(false);
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
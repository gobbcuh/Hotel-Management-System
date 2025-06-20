package Hotel.Management.System;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessReservations extends JFrame {
    JTable table;
    JButton process, back;

    ProcessReservations() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1090, 590);
        panel.setBackground(new Color(250, 213, 213));
        panel.setLayout(null);
        add(panel);

        JLabel title = new JLabel("Pending Reservations");
        title.setBounds(450, 10, 200, 25);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        panel.add(title);

        table = new JTable();
        table.setBounds(10, 40, 1070, 450);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        refreshTable();

        JLabel id = new JLabel("Reservation ID");
        id.setBounds(10, 15, 100, 16);
        id.setForeground(Color.BLACK);
        id.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(id);

        JLabel document = new JLabel("Document");
        document.setBounds(110, 15, 100, 16);
        document.setForeground(Color.BLACK);
        document.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(document);

        JLabel number = new JLabel("Number");
        number.setBounds(210, 15, 100, 16);
        number.setForeground(Color.BLACK);
        number.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(number);

        JLabel name = new JLabel("Name");
        name.setBounds(310, 15, 100, 16);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(name);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(410, 15, 100, 16);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(gender);

        JLabel country = new JLabel("Country");
        country.setBounds(510, 15, 100, 16);
        country.setForeground(Color.BLACK);
        country.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(country);

        JLabel room = new JLabel("Room");
        room.setBounds(610, 15, 100, 16);
        room.setForeground(Color.BLACK);
        room.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(room);

        JLabel checkin = new JLabel("Check-In Date");
        checkin.setBounds(710, 15, 100, 16);
        checkin.setForeground(Color.BLACK);
        checkin.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(checkin);

        JLabel duration = new JLabel("Duration");
        duration.setBounds(810, 15, 100, 16);
        duration.setForeground(Color.BLACK);
        duration.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(duration);

        JLabel price = new JLabel("Price");
        price.setBounds(910, 15, 100, 16);
        price.setForeground(Color.BLACK);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(price);

        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(1010, 15, 100, 16);
        deposit.setForeground(Color.BLACK);
        deposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(deposit);

        process = new JButton("Process Selected");
        process.setBounds(400, 500, 150, 30);
        process.setBackground(Color.BLACK);
        process.setForeground(Color.WHITE);
        panel.add(process);
        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a reservation", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String reservationId = table.getValueAt(selectedRow, 0).toString();
                String roomNumber = table.getValueAt(selectedRow, 6).toString();
                String checkInDate = table.getValueAt(selectedRow, 7).toString();

                try {
                    con c = new con();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String today = sdf.format(new Date());

                    if (!checkInDate.substring(0, 10).equals(today)) {
                        JOptionPane.showMessageDialog(null, "Can only process reservations for today", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String checkRoomQuery = "SELECT availability FROM room WHERE room_number = ?";
                    PreparedStatement checkStmt = c.connection.prepareStatement(checkRoomQuery);
                    checkStmt.setString(1, roomNumber);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next() && !rs.getString("availability").equals("Available")) {
                        JOptionPane.showMessageDialog(null, "Room is already occupied", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    checkStmt.close();

                    String fetchQuery = "SELECT * FROM reservations WHERE reservation_id = ?";
                    PreparedStatement fetchStmt = c.connection.prepareStatement(fetchQuery);
                    fetchStmt.setString(1, reservationId);
                    ResultSet reservationRs = fetchStmt.executeQuery();
                    if (reservationRs.next()) {
                        String customerQuery = "INSERT INTO customer (document, number, name, gender, country, room, checkintime, deposit, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement customerStmt = c.connection.prepareStatement(customerQuery);
                        customerStmt.setString(1, reservationRs.getString("document"));
                        customerStmt.setString(2, reservationRs.getString("number"));
                        customerStmt.setString(3, reservationRs.getString("name"));
                        customerStmt.setString(4, reservationRs.getString("gender"));
                        customerStmt.setString(5, reservationRs.getString("country"));
                        customerStmt.setString(6, reservationRs.getString("room_number"));
                        customerStmt.setString(7, checkInDate);
                        customerStmt.setString(8, reservationRs.getString("deposit"));
                        customerStmt.setString(9, reservationRs.getString("duration"));
                        customerStmt.setString(10, reservationRs.getString("price"));
                        customerStmt.executeUpdate();
                        customerStmt.close();

                        String updateRoomQuery = "UPDATE room SET availability = 'Occupied' WHERE room_number = ?";
                        PreparedStatement updateStmt = c.connection.prepareStatement(updateRoomQuery);
                        updateStmt.setString(1, roomNumber);
                        updateStmt.executeUpdate();
                        updateStmt.close();

                        String updateReservationQuery = "UPDATE reservations SET status = 'Confirmed' WHERE reservation_id = ?";
                        PreparedStatement updateResStmt = c.connection.prepareStatement(updateReservationQuery);
                        updateResStmt.setString(1, reservationId);
                        updateResStmt.executeUpdate();
                        updateResStmt.close();

                        JOptionPane.showMessageDialog(null, "Reservation Processed Successfully");
                        refreshTable();
                    }
                    fetchStmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error processing reservation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        back = new JButton("Back");
        back.setBounds(600, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setLayout(null);
        setSize(1100, 600);
        setLocation(176, 50);
        setVisible(true);
    }

    private void refreshTable() {
        try {
            con c = new con();
            String q = "SELECT * FROM reservations WHERE status = 'Pending'";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ProcessReservations();
    }
}
package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PickUp extends JFrame {
    private Choice carChoice;
    private JTable table;
    private JButton bookButton, completeButton;
    private String currentBookingId = null;
    private JTextField customerNumberField;

    PickUp() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 213, 213));
        panel.setBounds(5, 5, 790, 590);
        panel.setLayout(null);
        add(panel);

        JLabel PUS = new JLabel("Pick Up Service");
        PUS.setBounds(300, 40, 160, 25);
        PUS.setForeground(Color.BLACK);
        PUS.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(PUS);

        JLabel TOC = new JLabel("Type of Car");
        TOC.setBounds(32, 97, 89, 17);
        TOC.setForeground(Color.BLACK);
        TOC.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(TOC);

        carChoice = new Choice();
        carChoice.setBounds(123, 94, 150, 25);
        panel.add(carChoice);

        JLabel customerLabel = new JLabel("Customer ID");
        customerLabel.setBounds(32, 140, 89, 17);
        customerLabel.setForeground(Color.BLACK);
        customerLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(customerLabel);

        customerNumberField = new JTextField();
        customerNumberField.setBounds(123, 137, 150, 25);
        panel.add(customerNumberField);

        try {
            con C = new con();
            ResultSet resultSet = C.statement.executeQuery("select * from driver");
            while (resultSet.next()) {
                carChoice.add(resultSet.getString("car_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        table.setBounds(10, 233, 800, 250);
        table.setBackground(new Color(250, 213, 213));
        table.setForeground(Color.BLACK);
        panel.add(table);

        refreshTable();
        checkExistingBooking();

        JLabel name = new JLabel("Name");
        name.setBounds(24, 208, 46, 14);
        name.setForeground(Color.BLACK);
        panel.add(name);

        JLabel age = new JLabel("Age");
        age.setBounds(165, 208, 46, 14);
        age.setForeground(Color.BLACK);
        panel.add(age);

        JLabel gender = new JLabel("Sex");
        gender.setBounds(264, 208, 46, 14);
        gender.setForeground(Color.BLACK);
        panel.add(gender);

        JLabel company = new JLabel("Company");
        company.setBounds(366, 208, 100, 14);
        company.setForeground(Color.BLACK);
        panel.add(company);

        JLabel carName = new JLabel("Car Name");
        carName.setBounds(486, 208, 100, 14);
        carName.setForeground(Color.BLACK);
        panel.add(carName);

        JLabel available = new JLabel("Availability");
        available.setBounds(600, 208, 100, 14);
        available.setForeground(Color.BLACK);
        panel.add(available);

        JLabel location = new JLabel("Location");
        location.setBounds(700, 208, 100, 14);
        location.setForeground(Color.BLACK);
        panel.add(location);

        JButton display = new JButton("Display");
        display.setBounds(100, 500, 120, 30);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        panel.add(display);
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from driver where car_name = '" + carChoice.getSelectedItem() + "'";
                try {
                    con c = new con();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        bookButton = new JButton("Book Driver");
        bookButton.setBounds(230, 500, 120, 30);
        bookButton.setBackground(Color.BLACK);
        bookButton.setForeground(Color.WHITE);
        panel.add(bookButton);
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerNumber = customerNumberField.getText().trim();
                if (customerNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a customer ID.");
                    return;
                }

                try {
                    con c = new con();
                    // Check if customer has an active booking
                    String checkQuery = "select * from booking where customer_number = '" + customerNumber + "' and status = 'Active'";
                    ResultSet rs = c.statement.executeQuery(checkQuery);
                    if (rs.next()) {
                        currentBookingId = rs.getString("booking_id");
                        JOptionPane.showMessageDialog(null, "You already have an active booking. Complete it first.");
                        updateButtonStates();
                        return;
                    }

                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a driver to book.");
                        return;
                    }

                    String driverName = table.getValueAt(selectedRow, 0).toString();
                    String availability = table.getValueAt(selectedRow, 5).toString();
                    String carName = table.getValueAt(selectedRow, 4).toString();

                    if (!availability.equalsIgnoreCase("Available")) {
                        JOptionPane.showMessageDialog(null, "This driver is not available.");
                        return;
                    }

                    // Update driver status
                    String updateDriver = "update driver set available = 'Occupied' where name = '" + driverName + "'";
                    c.statement.executeUpdate(updateDriver);

                    // Record booking
                    String bookingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String insertBooking = "insert into booking (customer_number, driver_name, car_name, booking_time, status) values ('" + customerNumber + "', '" + driverName + "', '" + carName + "', '" + bookingTime + "', 'Active')";
                    c.statement.executeUpdate(insertBooking);

                    // Get the booking ID
                    ResultSet bookingRs = c.statement.executeQuery("select booking_id from booking where customer_number = '" + customerNumber + "' and status = 'Active' order by booking_time desc limit 1");
                    if (bookingRs.next()) {
                        currentBookingId = bookingRs.getString("booking_id");
                        System.out.println("New booking ID: " + currentBookingId);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to retrieve booking ID.");
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Driver " + driverName + " booked successfully!");
                    refreshTable();
                    updateButtonStates();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error booking driver: " + ex.getMessage());
                }
            }
        });

        completeButton = new JButton("Complete Booking");
        completeButton.setBounds(360, 500, 140, 30);
        completeButton.setBackground(Color.BLACK);
        completeButton.setForeground(Color.WHITE);
        completeButton.setEnabled(false);
        panel.add(completeButton);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentBookingId == null) {
                    JOptionPane.showMessageDialog(null, "No active booking to complete.");
                    return;
                }

                try {
                    con c = new con();
                    // Get driver name from booking
                    String getDriver = "select driver_name from booking where booking_id = '" + currentBookingId + "'";
                    ResultSet rs = c.statement.executeQuery(getDriver);
                    String driverName = null;
                    if (rs.next()) {
                        driverName = rs.getString("driver_name");
                    } else {
                        JOptionPane.showMessageDialog(null, "Booking not found.");
                        currentBookingId = null;
                        updateButtonStates();
                        return;
                    }

                    // Update booking status
                    String updateBooking = "update booking set status = 'Completed' where booking_id = '" + currentBookingId + "'";
                    c.statement.executeUpdate(updateBooking);

                    // Update driver availability
                    String updateDriver = "update driver set available = 'Available' where name = '" + driverName + "'";
                    c.statement.executeUpdate(updateDriver);

                    JOptionPane.showMessageDialog(null, "Booking completed for driver " + driverName + "!");
                    currentBookingId = null;
                    refreshTable();
                    updateButtonStates();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error completing booking: " + ex.getMessage());
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(510, 500, 120, 30);
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
        setSize(800, 600);
        setLocation(400, 55);
        setVisible(true);
    }

    private void refreshTable() {
        try {
            con C = new con();
            String q = "select * from driver";
            ResultSet resultSet = C.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkExistingBooking() {
        String customerNumber = customerNumberField.getText().trim();
        if (!customerNumber.isEmpty()) {
            try {
                con c = new con();
                String checkQuery = "select booking_id from booking where customer_number = '" + customerNumber + "' and status = 'Active'";
                ResultSet rs = c.statement.executeQuery(checkQuery);
                if (rs.next()) {
                    currentBookingId = rs.getString("booking_id");
                    System.out.println("Existing booking ID found: " + currentBookingId);
                    updateButtonStates();
                    JOptionPane.showMessageDialog(null, "You have an existing active booking. Complete it first.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateButtonStates() {
        bookButton.setEnabled(currentBookingId == null);
        completeButton.setEnabled(currentBookingId != null);
    }

    public static void main(String[] args) {
        new PickUp();
    }
}
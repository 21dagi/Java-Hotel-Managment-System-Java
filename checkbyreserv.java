
/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/
package managmentdatabase;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


@SuppressWarnings("serial")
public class checkbyreserv extends JFrame {
    JLabel titleLabel, messageLabel;
    JButton checkInButton;
    JTextField nameGuestTextField;
    Container container;
    Connection connection;

    public checkbyreserv(Connection connection) {
        getContentPane().setBackground(new Color(95, 158, 160));
        this.connection = connection;
        titleLabel = new JLabel("           Check-In Form");
        titleLabel.setBackground(new Color(119, 136, 153));
        titleLabel.setForeground(new Color(0, 0, 139));
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 23));
        messageLabel = new JLabel();
        checkInButton = new JButton("Check-In");
        checkInButton.setFont(new Font("Californian FB", Font.BOLD, 14));
        checkInButton.setForeground(new Color(0, 0, 128));
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();

        checkInButton.addActionListener(e -> {
            try {
                String guestName = nameGuestTextField.getText();
                if (guestName.isEmpty()) {
                    JOptionPane.showMessageDialog(checkbyreserv.this, "Please enter a guest name");
                    return;
                }

                int guestID = getGuestID(guestName);
                if (guestID == -1) {
                    JOptionPane.showMessageDialog(checkbyreserv.this, "Guest name not found. Please try again.");
                    nameGuestTextField.setText("");
                    nameGuestTextField.requestFocus();
                    return;
                }

                int roomID = getRoomID(guestID);
                int reservationID = getReservationID(guestID);

                if (roomID == -1 || reservationID == -1) {
                    JOptionPane.showMessageDialog(checkbyreserv.this, "Reservation details not found. Please try again.");
                    nameGuestTextField.setText("");
                    nameGuestTextField.requestFocus();
                    return;
                }
                String roomKey = getRoomKey(reservationID);
                insertCheckInDetails(reservationID, roomKey);

                JOptionPane.showMessageDialog(checkbyreserv.this, "Check-in details inserted successfully.");

                nameGuestTextField.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(checkbyreserv.this, "Error connecting to the database");
                ex.printStackTrace();
            }
        });
    }

    public void setBounds() {
        titleLabel.setBounds(0, 11, 484, 50);
        checkInButton.setBounds(68, 180, 100, 30);
        messageLabel.setBounds(120, 220, 300, 30);
        setSize(500, 300);
        setVisible(true);
    }

    public void addComponents() {
        container.add(titleLabel);
        container.add(checkInButton);
        container.add(messageLabel);

        nameGuestTextField = new JTextField();
        nameGuestTextField.setBackground(new Color(220, 220, 220));
        nameGuestTextField.setBounds(200, 100, 200, 30);
        getContentPane().add(nameGuestTextField);

        JButton checkInBackButton = new JButton("Proceed");
        checkInBackButton.setForeground(new Color(0, 0, 128));
        checkInBackButton.setFont(new Font("Californian FB", Font.BOLD, 15));
        checkInBackButton.setBounds(298, 180, 100, 30);
        checkInBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                menuH menuFrame = new menuH(connection);
                menuFrame.setVisible(true);
            }
        });
        getContentPane().add(checkInBackButton);

        JLabel lblGuestName = new JLabel("    Guest Name");
        lblGuestName.setForeground(new Color(0, 0, 128));
        lblGuestName.setFont(new Font("Californian FB", Font.BOLD, 15));
        lblGuestName.setBounds(49, 100, 150, 30);
        getContentPane().add(lblGuestName);
    }public int getGuestID(String guestName) throws SQLException {
        String query = "SELECT guest_id FROM Guest WHERE first_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, guestName);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("guest_id");
        } else {
            return -1;
        }
    }

    public int getRoomID(int guestID) throws SQLException {
        String query = "SELECT room_id FROM Reservation WHERE guest_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, guestID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("room_id");
        } else {
            return -1;
        }
    }

    public int getReservationID(int guestID) throws SQLException {
        String query = "SELECT reservation_id FROM Reservation WHERE guest_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, guestID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("reservation_id");
        } else {
            return -1;
        }
    }
    public String getRoomKey(int reservationID) throws SQLException {
        String query = "SELECT room_key FROM CheckIn WHERE reservation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, reservationID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("room_key");
        } else {
            return null; // or any default value you prefer
        }
    }

    public void insertCheckInDetails(int reservationID, String roomKey) throws SQLException {
    	
        String query = "INSERT INTO CheckIn (reservation_id, checkin_date, room_key) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, reservationID);
        statement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now())); // Assuming current date for check-in
        statement.setString(3, roomKey);
        statement.executeUpdate();
    }

    

      
    }

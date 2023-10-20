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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
@SuppressWarnings({ "serial", "unused" })
public class reserv extends JFrame {
    JLabel titleLabel, roomLabel, messageLabel;
    JComboBox<String> roomComboBox;
    JButton checkInButton;
    JTextField roomIDTextField; // Added JTextField for room ID input
    Container container;
    Connection connection;
    private JTextField nameguest;
    private JLabel lblGuestName;
    private JLabel lblSelectedRoomNo;

    public reserv(Connection connection) {
    	getContentPane().setBackground(new Color(176, 196, 222));
        this.connection=connection;
        titleLabel = new JLabel("           Reservation Form");
        titleLabel.setBackground(new Color(119, 136, 153));
        titleLabel.setForeground(new Color(0, 0, 139));
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 23));
        roomLabel = new JLabel("      Available Rooms:");
        roomLabel.setForeground(new Color(0, 0, 128));
        roomLabel.setFont(new Font("Californian FB", Font.BOLD, 15));
        messageLabel = new JLabel();
        roomComboBox = new JComboBox<>();
        roomComboBox.setBackground(new Color(70, 130, 180));
        checkInButton = new JButton("Reserve");
        checkInButton.setFont(new Font("Californian FB", Font.BOLD, 14));
        checkInButton.setForeground(new Color(0, 0, 128));
        roomIDTextField = new JTextField(); // Initialize the JTextField
        roomIDTextField.setBackground(new Color(220, 220, 220));
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();

        checkInButton.addActionListener(e -> {
            String selectedRoom = (String) roomComboBox.getSelectedItem();
            if (selectedRoom == null) {
                JOptionPane.showMessageDialog(reserv.this, "No available rooms matching the criteria");
            } else {
                try {
                    // Get the room ID from the text field
                    String roomIDText = roomIDTextField.getText();
                    if (roomIDText.isEmpty()) {
                        JOptionPane.showMessageDialog(reserv.this, "Please enter a room ID");
                        return;
                    }
                    int roomID = Integer.parseInt(roomIDText);

                    // Get the guest name from the text field
                    String guestName = nameguest.getText();
                    if (guestName.isEmpty()) {
                        JOptionPane.showMessageDialog(reserv.this, "Please enter a guest name");
                        return;
                    }

                    // Check if the guest name exists in the database
                    int guestID = getGuestID(guestName);
                    if (guestID == -1) {
                        JOptionPane.showMessageDialog(reserv.this, "Guest name not found. Please try again.");
                        nameguest.setText(""); // Clear the guest name text field
                        nameguest.requestFocus(); // Set focus on the guest name text field
                        loadAvailableRooms(); // Refresh available rooms
                        return;
                    }

                    // Insert reservation information into the database
                    insertReservation(guestID, roomID);

                    // Clear the fields after successful check-in
                    roomIDTextField.setText("");
                    nameguest.setText("");
                    loadAvailableRooms(); // Refresh available rooms

                    // Display success message
                    JOptionPane.showMessageDialog(reserv.this, "Reservation successful");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(reserv.this, "Invalid room ID");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(reserv.this, "Error connecting to the database");
                    ex.printStackTrace();
                }
            }
        });
    }

    public void setBounds() {
    	titleLabel.setBounds(0, 11, 484, 50);
        roomLabel.setBounds(39, 100, 150, 30);
        roomComboBox.setBounds(200, 100, 200, 30);
        checkInButton.setBounds(68, 259, 100, 30);
        messageLabel.setBounds(120, 300, 300, 30);
        roomIDTextField.setBounds(200, 180, 200, 30);
        // Set the size of the frame
        setSize(500, 400);
        
        // Set the frame to be visible
        setVisible(true);
    }

    public void addComponents() {
        container.add(titleLabel);
        container.add(roomLabel);
        container.add(roomComboBox);
        container.add(checkInButton);
        container.add(messageLabel);
        container.add(roomIDTextField); // Added the room ID text field to the container

        nameguest = new JTextField();
        nameguest.setBackground(new Color(220, 220, 220));
        nameguest.setBounds(200, 141, 200, 30);
        getContentPane().add(nameguest);

        JButton checkinback = new JButton("Proceed");
        checkinback.setForeground(new Color(0, 0, 128));
        checkinback.setFont(new Font("Californian FB", Font.BOLD, 15));
        checkinback.setBounds(298, 259, 100, 30);
        checkinback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the menuH class
            	 setVisible(false);
                menuH menuFrame = new menuH(connection);
                
                // Set the frame properties
         
                menuFrame.setVisible(true);
               
            }
        });
        getContentPane().add(checkinback);
        
        lblGuestName = new JLabel("    Guest Name");
        lblGuestName.setForeground(new Color(0, 0, 128));
        lblGuestName.setFont(new Font("Californian FB", Font.BOLD, 15));
        lblGuestName.setBounds(49, 141, 150, 30);
        getContentPane().add(lblGuestName);
        
        lblSelectedRoomNo = new JLabel("     Selected Room No.");
        lblSelectedRoomNo.setForeground(new Color(0, 0, 128));
        lblSelectedRoomNo.setFont(new Font("Californian FB", Font.BOLD, 15));
        lblSelectedRoomNo.setBounds(39, 188, 150, 30);
        getContentPane().add(lblSelectedRoomNo);
    }

    public void loadAvailableRooms() {
        roomComboBox.removeAllItems();

        try {
            // Fetch available room codes from the database
            String query = "SELECT room_id FROM Room WHERE available = 'AVAILABLE'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                // No available rooms matching the criteria
                JOptionPane.showMessageDialog(reserv.this, "No available rooms matching the criteria");
            } else {
            	System.out.println("Fetching available rooms...");
            	while (resultSet.next()) {
            	    int roomId = resultSet.getInt("room_id");
            	    roomComboBox.addItem(String.valueOf(roomId));
            	}
                }
            

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserv.this, "Error connecting to the database");
            e.printStackTrace();
        }
    }
    public int getGuestID(String guestName) throws SQLException {
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

    public UUID generateReservationId() {
        return UUID.randomUUID();
    }

    public void insertReservation(int guestID, int roomID) {
        try {
            // Prepare the SQL statement to insert a new reservation
            int reservationID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
            String query = "INSERT INTO Reservation (reservation_id, guest_id, room_id, checkin_date, checkout_date, _status, special_requirements) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationID);
            statement.setInt(2, guestID);
            statement.setInt(3, roomID);
            statement.setNull(4, java.sql.Types.DATE); // Set check-in date to null
            statement.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1))); // Assuming next day for check-out
            statement.setString(6, "Confirmed");
            statement.setString(7, ""); // Empty string for special requirements

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement
            statement.close();

            String updateQuery = "UPDATE Room SET available = 'Occupied' WHERE room_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, roomID);

            // Execute the update statement
            updateStatement.executeUpdate();

            // Close the update statement
            updateStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
    
    
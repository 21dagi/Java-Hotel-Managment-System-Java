

/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/
package managmentdatabase;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class infoguest extends JFrame {

	private JPanel contentPane;
	private Connection connection;

	public int maxAttempts = 1;
	public int currentAttempt = 0;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField roomNumField;
	private JTextField roomTypeField;
	private JTextField checkinDateField;
	private JTextField checkinDateField_1;
	private JTextField fname2;
	private JTextField lname2;
	

    // Other existing code

	private void retrieveAndSetCheckinDate(String firstName, String lastName) {
	    try {
	        String query = "SELECT checkin_date FROM Reservation JOIN Guest ON Reservation.guest_id = Guest.guest_id WHERE Guest.first_name = ? AND Guest.last_name = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, firstName);
	        statement.setString(2, lastName);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            Date checkinDate = resultSet.getDate("checkin_date");
	            if (checkinDate != null) {
	                checkinDateField_1.setText(checkinDate.toString());
	            } else {
	                checkinDateField_1.setText("null");
	            }
	            // Call other methods or perform additional operations
	        } else {
	            checkinDateField_1.setText("Guest not found");
	        }

	        resultSet.close();
	        statement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        checkinDateField_1.setText("Error retrieving check-in date");
	    }
	}
	public infoguest(Connection connection) {
		 checkinDateField = new JTextField();
	    this.connection = connection;
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(95, 158, 160));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JLabel lastNameLabel = new JLabel("Last Name");
	    lastNameLabel.setBounds(227, 81, 86, 14);
	    contentPane.add(lastNameLabel);

	    JLabel roomNumLabel = new JLabel("Room Number");
	    roomNumLabel.setBounds(227, 146, 86, 14);
	    contentPane.add(roomNumLabel);

	    JLabel roomTypeLabel = new JLabel("Room Type");
	    roomTypeLabel.setBounds(227, 112, 86, 14);
	    contentPane.add(roomTypeLabel);

	    JLabel checkinDateLabel = new JLabel("Check-in Date");
	    checkinDateLabel.setBounds(227, 178, 86, 14);
	    contentPane.add(checkinDateLabel);

	    JButton quitButton = new JButton("Quit");
	    quitButton.setFont(new Font("Castellar", Font.BOLD, 13));
	    quitButton.setForeground(new Color(0, 0, 128));
	    quitButton.setBackground(new Color(248, 248, 255));
	    quitButton.setBounds(160, 227, 89, 23);
	    quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the menuH class
            	 setVisible(false);
                menuH menuFrame = new menuH(connection);
                
                // Set the frame properties
         
                menuFrame.setVisible(true);
               
            }
        });
	    contentPane.add(quitButton);

	    JLabel titleLabel = new JLabel("Guest Information");
	    titleLabel.setBounds(227, 11, 207, 23);
	    titleLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 13));
	    titleLabel.setForeground(new Color(255, 255, 255));
	    contentPane.add(titleLabel);

	    firstNameField = new JTextField();
	    firstNameField.setBackground(new Color(192, 192, 192));
	    firstNameField.setBounds(314, 44, 86, 20);
	    contentPane.add(firstNameField);
	    firstNameField.setColumns(10);

	    JLabel firstNameLabel = new JLabel("First Name");
	    firstNameLabel.setBounds(227, 47, 62, 14);
	    contentPane.add(firstNameLabel);

	    lastNameField = new JTextField();
	    lastNameField.setBackground(new Color(192, 192, 192));
	    lastNameField.setBounds(314, 78, 86, 20);
	    lastNameField.setColumns(10);
	    contentPane.add(lastNameField);

	    roomNumField = new JTextField();
	    roomNumField.setBackground(new Color(192, 192, 192));
	    roomNumField.setBounds(314, 143, 86, 20);
	    roomNumField.setColumns(10);
	    contentPane.add(roomNumField);

	    roomTypeField = new JTextField();
	    roomTypeField.setBackground(new Color(192, 192, 192));
	    roomTypeField.setBounds(314, 109, 86, 20);
	    roomTypeField.setColumns(10);
	    contentPane.add(roomTypeField);

	    checkinDateField_1 = new JTextField();
	    checkinDateField_1.setBackground(new Color(192, 192, 192));
	    checkinDateField_1.setBounds(314, 172, 86, 20);
	    checkinDateField_1.setColumns(10);
	    contentPane.add(checkinDateField_1);
	    
	    JLabel lblGuest = new JLabel("           Guest ");
	    lblGuest.setBackground(new Color(255, 255, 255));
	    lblGuest.setForeground(new Color(255, 255, 255));
	    lblGuest.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 16));
	    lblGuest.setBounds(0, 24, 207, 23);
	    contentPane.add(lblGuest);
	    
	    fname2 = new JTextField();
	    fname2.setBackground(new Color(248, 248, 255));
	    fname2.setColumns(10);
	    fname2.setBounds(27, 78, 145, 20);
	    contentPane.add(fname2);
	    
	    JLabel lblIsFound = new JLabel("         Is Found");
	    lblIsFound.setForeground(new Color(255, 255, 255));
	    lblIsFound.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 15));
	    lblIsFound.setBounds(10, 178, 145, 38);
	    contentPane.add(lblIsFound);
	    
	    lname2 = new JTextField();
	    lname2.setBackground(new Color(245, 245, 245));
	    lname2.setColumns(10);
	    lname2.setBounds(27, 120, 145, 20);
	    contentPane.add(lname2);

	    

	    boolean guestFound = false;
	   
	        String firstName = JOptionPane.showInputDialog("Enter guest's first name:");
	        String lastName = JOptionPane.showInputDialog("Enter guest's last name:");

	        // Query the database to retrieve the guest information
	        try {
	            String query = "SELECT G.first_name, G.last_name, R.room_id " +
	                           "FROM Guest G " +
	                           "JOIN Reservation RS ON G.guest_id = RS.guest_id " +
	                           "JOIN Room R ON RS.room_id = R.room_id " +
	                           "WHERE G.first_name = ? AND G.last_name = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, firstName);
	            statement.setString(2, lastName);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                // Guest found
	                guestFound = true;
	                firstNameField.setText(resultSet.getString("first_name"));
	                lastNameField.setText(resultSet.getString("last_name"));
	                roomNumField.setText(resultSet.getString("room_id"));
	                fname2.setText(resultSet.getString("first_name"));
	                lname2.setText(resultSet.getString("last_name"));
	                // Retrieve room_type based on the retrieved room_id
	                int roomId = resultSet.getInt("room_id");
	                
	                try {
	                    String roomQuery = "SELECT room_type FROM Room WHERE room_id = ?";
	                    PreparedStatement roomStatement = connection.prepareStatement(roomQuery);
	                    roomStatement.setInt(1, roomId);
	                    ResultSet roomResult = roomStatement.executeQuery();

	                    if (roomResult.next()) {
	                        String roomType = roomResult.getString("room_type");
	                        roomTypeField.setText(roomType);
	                    } else {
	                        roomTypeField.setText("Room type not found");
	                    }

	                    roomResult.close();
	                    roomStatement.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        retrieveAndSetCheckinDate(firstName, lastName);
	    

	    if (!guestFound ) {
	    	JOptionPane.showMessageDialog(null, "Guest not found.");
	    	fname2.setText("Guest not found");
            lname2.setText("Guest not found");
	        return;
	    }
	}	
}
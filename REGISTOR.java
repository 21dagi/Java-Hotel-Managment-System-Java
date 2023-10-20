
/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/

package managmentdatabase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.Color;

public class REGISTOR extends JFrame {

	private JPanel contentPane;
	private JTextField guestIdField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	private boolean isGuestIdExists(String guestId) {
	    try {
	        String query = "SELECT guest_id FROM guest WHERE guest_id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, guestId);
	        ResultSet resultSet = statement.executeQuery();
	        return resultSet.next();
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(REGISTOR.this, "Error connecting to the database");
	        ex.printStackTrace();
	        return false;
	    }
	}
	public void clearFields() {
        guestIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
       
        phoneNumberField.setText("");
    }
	public REGISTOR(Connection connection) {
		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 355);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 128, 192));
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel guestIdLabel = new JLabel("     Guest id");
		guestIdLabel.setFont(new Font("Californian FB", Font.BOLD, 16));
		guestIdLabel.setForeground(new Color(255, 255, 255));
		guestIdLabel.setBounds(27, 49, 117, 23);
		contentPane.add(guestIdLabel);
		
		JLabel firstNameLabel = new JLabel("      First Name");
		firstNameLabel.setFont(new Font("Californian FB", Font.BOLD, 15));
		firstNameLabel.setForeground(new Color(255, 255, 255));
		firstNameLabel.setBounds(27, 97, 117, 23);
		contentPane.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("       Last Name");
		lastNameLabel.setFont(new Font("Californian FB", Font.BOLD, 15));
		lastNameLabel.setForeground(new Color(255, 255, 255));
		lastNameLabel.setBounds(27, 152, 126, 22);
		contentPane.add(lastNameLabel);
		
		JLabel phoneNumberLabel = new JLabel("    Phone Number");
		phoneNumberLabel.setFont(new Font("Californian FB", Font.BOLD, 15));
		phoneNumberLabel.setForeground(new Color(255, 255, 255));
		phoneNumberLabel.setBounds(27, 202, 126, 22);
		contentPane.add(phoneNumberLabel);
		
		guestIdField = new JTextField();
		guestIdField.setBackground(new Color(211, 211, 211));
		guestIdField.setBounds(154, 49, 126, 20);
		contentPane.add(guestIdField);
		guestIdField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setBackground(new Color(211, 211, 211));
		firstNameField.setColumns(10);
		firstNameField.setBounds(154, 97, 126, 20);
		contentPane.add(firstNameField);
		
		lastNameField = new JTextField();
		lastNameField.setBackground(new Color(211, 211, 211));
		lastNameField.setColumns(10);
		lastNameField.setBounds(154, 154, 126, 20);
		contentPane.add(lastNameField);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setBackground(new Color(211, 211, 211));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(154, 204, 126, 20);
		contentPane.add(phoneNumberField);
		
		JLabel sexLabel = new JLabel("    Sex");
		sexLabel.setFont(new Font("Californian FB", Font.BOLD, 17));
		sexLabel.setForeground(new Color(255, 255, 255));
		sexLabel.setBounds(392, 49, 68, 25);
		contentPane.add(sexLabel);
		
		JRadioButton sexMale = new JRadioButton("male");
		sexMale.setFont(new Font("Californian FB", Font.BOLD, 18));
		sexMale.setBackground(new Color(0, 64, 128));
		sexMale.setForeground(new Color(255, 255, 255));
		sexMale.setBounds(374, 96, 109, 23);
		contentPane.add(sexMale);
		
		JRadioButton sexFemale = new JRadioButton("Feamle");
		sexFemale.setFont(new Font("Californian FB", Font.BOLD, 18));
		sexFemale.setForeground(new Color(255, 255, 255));
		sexFemale.setBackground(new Color(0, 64, 128));
		sexFemale.setBounds(374, 151, 109, 23);
		contentPane.add(sexFemale);
		
		JLabel message = new JLabel("       Register Guest");
		message.setForeground(new Color(255, 255, 255));
		message.setFont(new Font("Courier New", Font.BOLD, 20));
		message.setBounds(80, 0, 300, 50);
		contentPane.add(message);
		
		JButton registerButton = new JButton("Register");
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBackground(new Color(0, 128, 128));
		registerButton.setBounds(110, 256, 100, 30);
		contentPane.add(registerButton);
		
		JButton backbt = new JButton("Finished");
		backbt.setForeground(new Color(255, 255, 255));
		backbt.setBackground(new Color(0, 128, 128));
		backbt.setBounds(311, 256, 100, 30);
		backbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the menuH class
            	 setVisible(false);
                menuH menuFrame = new menuH(connection);
                
                // Set the frame properties
         
                menuFrame.setVisible(true);
               
            }
        });
		contentPane.add(backbt);
		registerButton.addActionListener(e -> {
		    String guestId = guestIdField.getText();
		    String firstName = firstNameField.getText();
		    String lastName = lastNameField.getText();
		    String sex = sexMale.isSelected() ? "Male" : "Female";
		    String phoneNumber = phoneNumberField.getText();

		    if (guestId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
		        JOptionPane.showMessageDialog(REGISTOR.this, "Please fill in all fields");
		    } else {
		        if (isGuestIdExists(guestId)) {
		            JOptionPane.showMessageDialog(REGISTOR.this, "Guest ID already exists");
		        } else {
		            try {
		                // Create a prepared statement for inserting guest information
		                String query = "INSERT INTO guest (guest_id, first_name, last_name, sex, phone_number) VALUES (?, ?, ?, ?, ?)";
		                PreparedStatement statement = connection.prepareStatement(query);
		                statement.setString(1, guestId);
		                statement.setString(2, firstName);
		                statement.setString(3, lastName);
		                statement.setString(4, sex);
		                statement.setString(5, phoneNumber);

		                // Execute the query
		                int rowsInserted = statement.executeUpdate();
		                if (rowsInserted > 0) {
		                    JOptionPane.showMessageDialog(REGISTOR.this, "Guest registered successfully");
		                    clearFields();
		                } else {
		                    JOptionPane.showMessageDialog(REGISTOR.this, "Failed to register guest");
		                    sexMale.setSelected(true);
		                }

		                // Close the statement
		                statement.close();
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(REGISTOR.this, "Error connecting to the database");
		                ex.printStackTrace();
		            }
		        }
		    }
		});
	}}
	


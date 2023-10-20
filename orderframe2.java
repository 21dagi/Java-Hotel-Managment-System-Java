

/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/
package managmentdatabase;



import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class orderframe2 extends JFrame implements ActionListener {
    private Connection connection;
    @SuppressWarnings("unused")
    private JPanel contentPane;
    private JTextField itemIDField;
    private JTextField guestIDField;
    private JTextField guestNameField;
    private JPanel panel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    orderframe2 frame = new orderframe2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public orderframe2() {
        connection = Hotel.getConnection();
        setTitle("Place Order");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(95, 158, 160));
        panel_1.setLayout(null);
        

        JLabel itemIDLabel = new JLabel("      Item ID:");
        itemIDLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
        itemIDLabel.setForeground(new Color(255, 255, 255));
        itemIDLabel.setBounds(0, 188, 102, 30);
        panel_1.add(itemIDLabel);

        itemIDField = new JTextField();
        itemIDField.setBackground(new Color(220, 220, 220));
        itemIDField.setBounds(140, 188, 180, 30);
        panel_1.add(itemIDField);

        JLabel guestIDLabel = new JLabel("    Guest ID:");
        guestIDLabel.setForeground(new Color(255, 255, 255));
        guestIDLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        guestIDLabel.setBounds(0, 75, 122, 30);
        panel_1.add(guestIDLabel);

        guestIDField = new JTextField();
        guestIDField.setBackground(new Color(220, 220, 220));
        guestIDField.setBounds(140, 75, 180, 30);
        panel_1.add(guestIDField);

        JLabel guestNameLabel = new JLabel("   Guest Name:");
        guestNameLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
        guestNameLabel.setForeground(new Color(255, 255, 255));
        guestNameLabel.setBounds(0, 132, 122, 30);
        panel_1.add(guestNameLabel);

        guestNameField = new JTextField();
        guestNameField.setBackground(new Color(220, 220, 220));
        guestNameField.setBounds(140, 132, 180, 30);
        panel_1.add(guestNameField);
        JPanel itemPanel = new JPanel();
        itemPanel.setBackground(new Color(192, 192, 192));
        itemPanel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setBounds(370, 50, 180, 280);
        panel_1.add(scrollPane);
        
      

        JButton submitButton = new JButton("Submit");
        submitButton.setForeground(new Color(255, 255, 255));
        submitButton.setFont(new Font("Elephant", Font.PLAIN, 13));
        submitButton.setBackground(new Color(0, 139, 139));
        submitButton.setBounds(39, 300, 100, 30);
        submitButton.addActionListener(this);
        panel_1.add(submitButton);

        JButton finishButton = new JButton("Finish");
        finishButton.setForeground(new Color(255, 255, 255));
        finishButton.setFont(new Font("Elephant", Font.PLAIN, 13));
        finishButton.setBackground(new Color(0, 139, 139));
        finishButton.setBounds(231, 300, 89, 30);
        finishButton.addActionListener(this);
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the menuH class
            	 setVisible(false);
                menuH menuFrame = new menuH(connection);
                
                // Set the frame properties
         
                menuFrame.setVisible(true);
               
            }
        });
        panel_1.add(finishButton);
        int y = 0; // Vertical position for displaying data rows
        String[] foodNames = {"Pizza", "Burger", "Pasta"};
        int[] itemIDs = {1, 2, 3};

        // Iterate through the data and display it in the GUI
        for (int i = 0; i < foodNames.length; i++) {
            JLabel lblFoodNoValue = new JLabel("Item ID: " + itemIDs[i]);
            lblFoodNoValue.setBounds(10, y + 50, 180, 14);
            itemPanel.add(lblFoodNoValue);

            JLabel lblFoodNameValue = new JLabel(foodNames[i]);
            lblFoodNameValue.setBounds(110, y + 50, 180, 14);
            itemPanel.add(lblFoodNameValue);

            y += 20; // Increment the vertical position for the next row
        }
        Container container = getContentPane();
        container.add(panel_1);
        
        JLabel lblNewLabel = new JLabel("                       Order Table");
        lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 20));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(87, 11, 398, 33);
        panel_1.add(lblNewLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            String itemID = itemIDField.getText();
            String guestID = guestIDField.getText();
            String guestName = guestNameField.getText();

            if (itemID.isEmpty() || guestID.isEmpty() || guestName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (!checkGuestName(guestID, guestName)) {
                JOptionPane.showMessageDialog(this, "Guest name does not match the given guest ID. Please try again.");
                return;
            }

            if (insertOrder(guestID, itemID)) {
                JOptionPane.showMessageDialog(this, "Order placed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error occurred while placing the order.");
            }
        } else if (e.getActionCommand().equals("Finish")) {
            dispose();
        }
    }

    private boolean checkGuestName(String guestID, String guestName) {
        boolean guestNameMatchesID = false;
        try {
            // Prepare the SQL statement to check if the guest ID and guest name exist in the database
            String sql = "SELECT COUNT(*) FROM Guest WHERE guest_id = ? AND first_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guestID);
            statement.setString(2, guestName);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            // Check the count of matching rows
            int count = resultSet.getInt(1);
            guestNameMatchesID = count > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return guestNameMatchesID;
    }

    private boolean insertOrder(String guestID, String itemID) {
        try {
        	int orderID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
            // Prepare the SQL statement to insert an order into the database
        	 String sql = "INSERT INTO O_rder (order_id, guest_id, item_id, quantity) VALUES (?, ?, ?, ?)";
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, orderID);
             statement.setInt(2, Integer.parseInt(guestID));
             statement.setInt(3, Integer.parseInt(itemID));
             statement.setInt(4, 1); // Sample quantity

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Return false if an error occurs during insertion
        }
    }
}

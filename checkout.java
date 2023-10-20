

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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class checkout extends JFrame {

    private JPanel contentPane;
    private JTextField orderfield;
    private JTextField roomfield;
    private JTextField totalfield;
    private Connection connection;
    
    
    private void calculateTotalPrice(String firstName) {
        try {
            String query = "SELECT IFNULL(SUM(RM.price_per_night), 0) AS room_price " +
                    "FROM Reservation R " +
                    "JOIN Room RM ON R.room_id = RM.room_id " +
                    "JOIN Guest G ON R.guest_id = G.guest_id " +
                    "WHERE G.first_name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            double roomPrice = 0.0;
            if (resultSet.next()) {
                roomPrice = resultSet.getDouble("room_price");
            }

            resultSet.close();
            statement.close();

            query = "SELECT IFNULL(SUM(BF.price * O.quantity), 0) AS order_price " +
                    "FROM O_rder O " +
                    "JOIN BestFoodDrink BF ON O.item_id = BF.item_id " +
                    "JOIN Guest G ON O.guest_id = G.guest_id " +
                    "WHERE G.first_name = ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            resultSet = statement.executeQuery();

            double orderPrice = 0.0;
            if (resultSet.next()) {
                orderPrice = resultSet.getDouble("order_price");
            }

            resultSet.close();
            statement.close();

            double totalPrice = roomPrice + orderPrice;
            totalfield.setText(String.format("%.2f", totalPrice));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void calculateRoomPrice(String firstName) {
        try {
            String query = "SELECT IFNULL(SUM(RM.price_per_night), 0) AS total_price " +
                    "FROM Reservation R " +
                    "JOIN Room RM ON R.room_id = RM.room_id " +
                    "JOIN Guest G ON R.guest_id = G.guest_id " +
                    "WHERE G.first_name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double totalPrice = resultSet.getDouble("total_price");
                roomfield.setText(String.format("%.2f", totalPrice));
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateOrderPrice(String firstName) {
        try {
            String query = "SELECT IFNULL(SUM(BF.price * O.quantity), 0) AS total_price " +
                    "FROM Guest G " +
                    "LEFT JOIN O_rder O ON G.guest_id = O.guest_id " +
                    "LEFT JOIN BestFoodDrink BF ON O.item_id = BF.item_id " +
                    "WHERE G.first_name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double totalPrice = resultSet.getDouble("total_price");
                orderfield.setText(String.format("%.2f", totalPrice));
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public checkout(Connection connection) {
        this.connection = connection;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 368);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(70, 130, 180));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Orderscost = new JLabel("Order Cost");
        Orderscost.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 14));
        Orderscost.setBounds(67, 88, 108, 28);
        contentPane.add(Orderscost);

        JLabel roomcost = new JLabel("Room Cost");
        roomcost.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 14));
        roomcost.setBounds(235, 90, 102, 28);
        contentPane.add(roomcost);

        JLabel totalcost = new JLabel("Total Cost");
        totalcost.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 14));
        totalcost.setBounds(159, 155, 108, 28);
        contentPane.add(totalcost);

        orderfield = new JTextField();
        orderfield.setBackground(new Color(173, 216, 230));
        orderfield.setBounds(59, 129, 86, 20);
        contentPane.add(orderfield);
        orderfield.setColumns(10);

        roomfield = new JTextField();
        roomfield.setBackground(new Color(135, 206, 235));
        roomfield.setColumns(10);
        roomfield.setBounds(245, 129, 86, 20);
        contentPane.add(roomfield);

        totalfield = new JTextField();
        totalfield.setBackground(new Color(135, 206, 235));
        totalfield.setColumns(10);
        totalfield.setBounds(159, 194, 86, 20);
        contentPane.add(totalfield);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(95, 158, 160));
        panel.setBounds(0, 0, 434, 74);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Guest checked Out");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel.setBounds(119, 21, 264, 29);
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(95, 158, 160));
        panel_1.setLayout(null);
        panel_1.setBounds(0, 234, 434, 95);
        contentPane.add(panel_1);
        
        JLabel lblThankYouFor = new JLabel("Thank You For Choosing Us");
        lblThankYouFor.setForeground(new Color(255, 255, 255));
        lblThankYouFor.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 16));
        lblThankYouFor.setBounds(91, 0, 333, 29);
        panel_1.add(lblThankYouFor);
        
                JButton ckquit = new JButton("quit");
                ckquit.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 13));
                ckquit.setBackground(new Color(32, 178, 170));
                ckquit.setBounds(261, 50, 89, 23);
                ckquit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0); // Terminate the application
                    }
                });
                panel_1.add(ckquit);
                
                JButton returnmenuebt = new JButton("Return");
                returnmenuebt.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 13));
                returnmenuebt.setBackground(new Color(32, 178, 170));
                returnmenuebt.setBounds(73, 50, 89, 23);
                returnmenuebt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Create an instance of the menuH class
                    	 setVisible(false);
                        menuH menuFrame = new menuH(connection);
                        
                        // Set the frame properties
                 
                        menuFrame.setVisible(true);
                       
                    }
                });
                panel_1.add(returnmenuebt);

        // Prompt user for guest's first name using JOptionPane
        String firstName;
        boolean guestExists = false;

        while (!guestExists) {
            firstName = JOptionPane.showInputDialog("Enter guest's first name:");
            
            // Check if guest exists in the database
            guestExists = checkGuestExists(firstName);
            calculateOrderPrice(firstName);
            calculateRoomPrice(firstName);
            calculateTotalPrice(firstName);
            if (!guestExists) {
                JOptionPane.showMessageDialog(null, "Guest does not exist. Please try again.");
            }
        }
       
        setVisible(true);
        
        
    }

    private boolean checkGuestExists(String firstName) {
        try {
            String query = "SELECT COUNT(*) FROM Guest WHERE first_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}

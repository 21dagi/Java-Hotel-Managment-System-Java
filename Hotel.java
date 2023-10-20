/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/


package managmentdatabase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Hotel extends JFrame {


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Hotel frame = new Hotel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/datafinal";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Hotel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 632, 391);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(70, 130, 180));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Java Hotel LogIn");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 17));
        lblNewLabel.setBounds(39, 211, 229, 35);
        contentPane.add(lblNewLabel);

        ImageIcon imageIcon = new ImageIcon("E:\\SOURCES\\exlipse workshop\\databasejava\\images\\login.png"); // Replace with the actual path to your image file
        JLabel pictureLabel = new JLabel(imageIcon);
        pictureLabel.setBounds(75, 72, 121, 128);
        contentPane.add(pictureLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 250));
        panel.setBounds(299, 0, 317, 352);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Login");
        lblNewLabel_1.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel_1.setBounds(135, 32, 113, 39);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setBackground(new Color(0, 128, 64));

        JLabel lblNewLabel_2 = new JLabel("User name");
        lblNewLabel_2.setFont(new Font("Rockwell", Font.BOLD, 15));
        lblNewLabel_2.setBounds(31, 98, 100, 20);
        panel.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBackground(new Color(220, 220, 220));
        textField.setBounds(31, 129, 217, 30);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Password");
        lblNewLabel_2_1.setFont(new Font("Rockwell", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(31, 170, 100, 20);
        panel.add(lblNewLabel_2_1);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(220, 220, 220));
        passwordField.setBounds(31, 201, 217, 30);
        panel.add(passwordField);

        JButton btnNewButton = new JButton("Sign In");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(70, 130, 180));
        btnNewButton.setFont(new Font("Rockwell", Font.BOLD, 15));
        btnNewButton.setBounds(107, 273, 89, 23);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(username, password)) {
                	setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login successful");
                    Connection connection = getConnection();
                    menuH menu = new menuH(connection);
                   menu.setSize(600, 400);
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }

			
        });
    }

    private boolean validateLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT * FROM users WHERE username = ? AND pass = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
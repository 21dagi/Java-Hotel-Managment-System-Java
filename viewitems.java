/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/

package managmentdatabase;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;


@SuppressWarnings("serial")
public class viewitems extends JFrame implements ActionListener{

    private JPanel contentPane;
	private Connection connection;
    public viewitems(Connection connection) {
    	this.connection = connection;
        setTitle("VIEW FOOD FOR USER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 635, 373);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFoodNo = new JLabel(" Food_id");
        lblFoodNo.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        lblFoodNo.setBounds(24, 20, 103, 32);
        contentPane.add(lblFoodNo);

        JLabel lblFoodName = new JLabel("Food_name");
        lblFoodName.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        lblFoodName.setBounds(125, 29, 103, 14);
        contentPane.add(lblFoodName);

        JLabel lblDescription = new JLabel("  DESCRIPTION");
        lblDescription.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblDescription.setBounds(254, 30, 138, 14);
        contentPane.add(lblDescription);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        lblPrice.setBounds(470, 29, 110, 14);
        contentPane.add(lblPrice);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(24, 56, 595, 216);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton viewbt = new JButton("Order");
        viewbt.setForeground(new Color(255, 255, 255));
        viewbt.setBackground(new Color(119, 136, 153));
        viewbt.setFont(new Font("Castellar", Font.BOLD, 12));
        viewbt.setBounds(372, 283, 89, 23);
        contentPane.add(viewbt);
        viewbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewitems.this.setVisible(false); 
                orderframe2 orderFrame = new orderframe2();
                orderFrame.setVisible(true);
            }
        });

        JButton viewquitbt = new JButton("Back ");
        viewquitbt.setForeground(new Color(255, 255, 255));
        viewquitbt.setBackground(new Color(119, 136, 153));
        viewquitbt.setFont(new Font("Castellar", Font.BOLD, 12));
        viewquitbt.setBounds(108, 283, 89, 23);
        viewquitbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the menuH class
            	 setVisible(false);
                menuH menuFrame = new menuH(connection);
                
                // Set the frame properties
         
                menuFrame.setVisible(true);
               
            }
        });
        contentPane.add(viewquitbt);
        
        // Retrieve data from the BestFoodDrink table and populate it in the GUI
        // Replace this section with your database connection and retrieval code
        // ...

        // Sample data for demonstration
        int y = 0; // Vertical position for displaying data rows
        String[] foodNames = {"Pizza", "Burger", "Pasta","Sandwitch", "Juice","Water", "Beer", "Fanta","Tea", "COffee"};
        String[] fooddiscription = {"arugula, pancetta, and truffles", "  lettuce, bacon, mayonnaise", "beans, tomatoes, tuna, olives",
        		"vegetables, sliced cheese or meat","fresh fruit ","10L","Habesha,Dashin,Arada","Soft Drink","Yellow and Normal","Nescafe"};
        double[] prices = {9.99, 5.99, 7.99,100.00,120.25,30.99,21.50,19.99,35.50,66.99};

        // Iterate through the data and display it in the GUI
        for (int i = 0; i < foodNames.length; i++) {
            JLabel lblFoodNoValue = new JLabel(String.valueOf(i + 1));
            lblFoodNoValue.setBounds(10, y, 68, 14);
            panel.add(lblFoodNoValue);

            JLabel lblFoodNameValue = new JLabel(foodNames[i]);
            lblFoodNameValue.setBounds(95, y, 103, 14);
            panel.add(lblFoodNameValue);

   
            JLabel lblDescriptionValue = new JLabel(fooddiscription[i]); // No description column in BestFoodDrink table
            lblDescriptionValue.setBounds(200, y,200, 14);
            panel.add(lblDescriptionValue);

            JLabel lblPriceValue = new JLabel(String.valueOf(prices[i]));
            lblPriceValue.setBounds(450, y, 49, 14);
            panel.add(lblPriceValue);

            y += 20; // Increment the vertical position for the next row
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
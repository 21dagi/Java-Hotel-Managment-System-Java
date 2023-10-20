


/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/
package managmentdatabase;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class menuH extends JFrame {
   
    public static Connection connection;

	public menuH(Connection connection) {
    	this.connection = connection;
    	getContentPane().setBackground(new Color(47, 79, 79));
    	getContentPane().setLayout(null);
    	setSize(653,444);
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(0, 139, 139));
    	panel.setBounds(0, 0, 204, 494);
    	getContentPane().add(panel);
    	panel.setLayout(null);
    	
ImageIcon imageIcon = new ImageIcon("E:\\SOURCES\\exlipse workshop\\databasejava\\images\\Hotel.png"); // Replace with the actual path to your image file
     
    	JLabel lblMainMenu = new JLabel("     Main Menu");
    	lblMainMenu.setForeground(new Color(255, 255, 255));
    	lblMainMenu.setFont(new Font("Modern No. 20", Font.BOLD, 21));
    	lblMainMenu.setBounds(10, 0, 164, 32);
    	panel.add(lblMainMenu);
    	
    	JButton btnNewButton_1 = new JButton("CheckIn");
    	btnNewButton_1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			 setVisible(false); // Hide the current frame (menuH frame)
    			 checks frame2 = new  checks(connection);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setVisible(true);
    		}
    	});
    	btnNewButton_1.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	btnNewButton_1.setBounds(22, 96, 152, 32);
    	panel.add(btnNewButton_1);
    	
    	JButton orderbt = new JButton("Orders");
    	orderbt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	orderbt.setBounds(22, 152, 152, 32);
    	orderbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        viewitems viewitem = new viewitems(connection);
    	        viewitem.setVisible(true); // Show the RegisterFrame
    	    }
    	});
    	panel.add(orderbt);
    	
    	JButton infobt = new JButton("Guest Infoormation");
    	infobt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	infobt.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			 EventQueue.invokeLater(new Runnable() {
    			        public void run() {
    			            try {
    			            	setVisible(false);
    			                infoguest frame = new infoguest(connection);
    			                frame.setVisible(true);
    			            } catch (Exception e) {
    			                e.printStackTrace();
    			            }
    			        }
    			    });
    		}
    	});
    	infobt.setBounds(22, 204, 152, 32);
    	panel.add(infobt);
    	
    	JButton checkoutbt = new JButton("Check Out");
    	checkoutbt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	checkoutbt.setBounds(22, 257, 152, 32);
    	checkoutbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        checkout frame = new checkout(connection);
                frame.setVisible(true);
    	    }
    	});
    	panel.add(checkoutbt);
    	
    	JButton registerbt = new JButton("Registor Guest");
    	registerbt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	registerbt.setBounds(20, 43, 152, 32);
    	panel.add(registerbt);
    	registerbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        REGISTOR registerFrame = new REGISTOR(connection);
    	        registerFrame.setVisible(true); // Show the RegisterFrame
    	    }
    	});
    	
    	JButton quitbt = new JButton("Quit");
    	quitbt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 13));
    	quitbt.setBounds(22, 311, 152, 32);
    	quitbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminate the application
            }
        });
    	panel.add(quitbt);
    	
    	JLabel lblNewLabel = new JLabel("  Welcome ");
    	lblNewLabel.setForeground(new Color(0, 206, 209));
    	lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 40));
    	lblNewLabel.setBounds(289, 144, 314, 68);
    	getContentPane().add(lblNewLabel);
    	
    	JLabel lblNewLabel_2 = new JLabel("      Java Hotel");
    	lblNewLabel_2.setForeground(new Color(0, 206, 209));
    	lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.ITALIC, 30));
    	lblNewLabel_2.setBounds(273, 223, 314, 68);
    	getContentPane().add(lblNewLabel_2);
    	
    	JLabel pictureLabel = new JLabel(imageIcon);
    	pictureLabel.setForeground(new Color(255, 255, 255));
    	pictureLabel.setBounds(331, 11, 154, 125);
    	getContentPane().add(pictureLabel);
        
    }

    public static void main(String[] args) {
    	menuH m = new menuH(connection);
    	
}	
}
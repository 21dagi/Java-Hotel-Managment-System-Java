
/* DAGMAWI NEGUSSIE
computer science and engineering student
21dagmawinegussie@gmail.com
oct-20-2023
ETHIOPIA*/
package managmentdatabase;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class checks extends JFrame {

	private JPanel contentPane;

	
	public checks(Connection connection) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton chekbt = new JButton("check");
		chekbt.setBounds(83, 10, 274, 49);
		chekbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        checkinframe2 frame = new checkinframe2(connection);
                frame.setVisible(true);
    	    }
    	});
		contentPane.add(chekbt);
		
		JButton resbt = new JButton("reserv");
		resbt.setBounds(83, 81, 274, 49);
		resbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        reserv res = new reserv(connection);
               res.setVisible(true);
    	    }
    	});
		contentPane.add(resbt);
		
		JButton crevbt = new JButton("check reserved");
		crevbt.setBounds(83, 157, 274, 49);
		crevbt.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        setVisible(false); // Hide the current frame (menuH frame)
    	        checkbyreserv chec = new checkbyreserv(connection);
                chec.setVisible(true);
    	    }
    	});
		contentPane.add(crevbt);
	}
}

package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JRequestsLocationFrame extends JFrame implements ActionListener {
	private JLabel labelSelectRequests = new JLabel("Please select the requests:");
	private JAmbient ambient;
	private JButton buttonOK = new JButton("OK");
	private JInitialFrame initialFrame;
	
	public JRequestsLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Requests selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialFrame = initialFrame;
		buttonOK.addActionListener(this);
		this.ambient = ambient;
		this.ambient.setState(JAmbient.SETTING_REQUESTS);
		
		
		add(labelSelectRequests, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
		add(buttonOK, BorderLayout.SOUTH);
	
		setSize(500, 500);
		//setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOK) {
			JOptionPane.showMessageDialog(this, "That's all, let's work on it!");
			
			ambient.getCamino().shortestPath();
			System.out.println("Shortest path con éxito, pintar camino seguido");
		}
	}
	
	

}

package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JRequestsLocationFrame extends JFrame {
	private JLabel labelSelectRequests = new JLabel("Please select the requests:");
	private JAmbient ambient;
	private JInitialFrame initialFrame;
	
	public JRequestsLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Requests selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialFrame = initialFrame;
		
		this.ambient = ambient;
		//this.ambient.setState(JAmbient.SETTING_REQUESTS);
		
		add(labelSelectRequests, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
	
		setSize(500, 500);
		setVisible(true);
	}
	

}

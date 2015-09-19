package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JRequestsLocationFrame extends JFrame {
	private JLabel labelSelectRequests = new JLabel("Please select the requests:");
	private int numberOfRequests;
	private JAmbient ambient;
	
	public JRequestsLocationFrame(JAmbient ambient, int numberOfRequests) {
		super("Requests selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ambient = ambient;
		this.ambient.setState(JAmbient.SETTING_REQUESTS);
		this.numberOfRequests = numberOfRequests;
		
		add(labelSelectRequests, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
		
		
	}

}

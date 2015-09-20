package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JAgentLocationFrame extends JFrame {
	private JLabel labelSelectLocation = 
			new JLabel("Please select the location of the taxi agent:");
	JAmbient ambient;
	private JInitialFrame initialFrame;

	public JAgentLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Agent Location");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialFrame = initialFrame;
		
		this.ambient = ambient;
		//this.ambient.setState(JAmbient.SETTING_TAXI_AGENT);
		
		add(labelSelectLocation, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
		
		setSize(500, 500);
		setVisible(true);
	}

}

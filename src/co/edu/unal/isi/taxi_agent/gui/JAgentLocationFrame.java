package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JAgentLocationFrame extends JFrame implements ActionListener {
	private JLabel labelSelectLocation = 
			new JLabel("Please select the location of the taxi agent:");
	private JButton buttonOK = new JButton("OK");
	JAmbient ambient;
	private JInitialFrame initialFrame;
	private JRequestsLocationFrame requestsLocationFrame;

	public JAgentLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Agent Location");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialFrame = initialFrame;
		
		this.ambient = ambient;
		buttonOK.addActionListener(this);
		//this.ambient.setState(JAmbient.SETTING_TAXI_AGENT);
		
		add(labelSelectLocation, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
		add(buttonOK, BorderLayout.SOUTH);
		
		setSize(500, 500);
		//setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOK) {
			this.setVisible(false);
			ambient.setState(JAmbient.SETTING_REQUESTS);
			requestsLocationFrame = new JRequestsLocationFrame(ambient, initialFrame);
			requestsLocationFrame.setVisible(true);
		}
	}

}

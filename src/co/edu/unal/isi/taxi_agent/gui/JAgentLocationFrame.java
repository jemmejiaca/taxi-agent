package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JAgentLocationFrame extends JFrame {
	private JLabel labelSelectLocation = 
			new JLabel("Please select the location of the taxi agent:");
	private JAmbient ambient;

	public JAgentLocationFrame(int rows, int cols) {
		super("Agent Location");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ambient = new JAmbient(rows, cols);

		add(labelSelectLocation, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
	}

}

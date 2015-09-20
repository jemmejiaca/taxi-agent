package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JRoadLocationFrame extends JFrame implements ActionListener {
	private JLabel labelInputRoad = new JLabel("Please enter the road's map:");
	private JAmbient ambient;
	private JButton buttonInputRoad = new JButton("Input Road");
	private JInitialFrame initialFrame;
	private JAgentLocationFrame agentLocationFrame;
	
	public JRoadLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Road allocation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialFrame = initialFrame;
		
		this.ambient = ambient;
		//this.ambient.setState(JAmbient.SETTING_ROAD);
		buttonInputRoad.addActionListener(this);
		add(labelInputRoad, BorderLayout.NORTH);
		add(this.ambient, BorderLayout.CENTER);
		add(buttonInputRoad, BorderLayout.SOUTH);
		
		setSize(500, 500);
		//setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source =  e.getSource();
		if (source == buttonInputRoad) {
			System.out.println("OK");
			this.setVisible(false);
			ambient.setState(JAmbient.SETTING_TAXI_AGENT);
			agentLocationFrame = new JAgentLocationFrame(ambient, initialFrame);
			agentLocationFrame.setVisible(true);
		}
		
		
	}

}

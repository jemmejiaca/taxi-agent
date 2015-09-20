package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JInitialFrame extends JFrame implements ActionListener {
	
	private JLabel labelTitle = new JLabel("Taxi Agent Simulator");
	private JLabel labelSize = new JLabel("Size of the world");
	private JLabel labelRows = new JLabel("Rows:");
	private JLabel labelCols = new JLabel("Columns:");
	private JLabel labelRequestType = new JLabel("Request type");
	
	private JTextField fieldRows = new JTextField(3);
	private JTextField fieldCols = new JTextField(3);
	
	private JRadioButton radioButtonKnown = new JRadioButton("Known");
	private JRadioButton radioButtonUnknown = new JRadioButton("Unknown");
	
	private JButton buttonOK = new JButton("OK");
	
	private JPanel panelSize = new JPanel(new FlowLayout());
	private JPanel panelRequest = new JPanel(new FlowLayout());
	private JPanel panelInitInfo = new JPanel(new GridLayout(4, 1, 2, 2));
	
	private ButtonGroup group = new ButtonGroup();
	
	private JAmbient ambient;
	private JRoadLocationFrame roadLocationFrame;
	private JAgentLocationFrame agentLocationFrame;
	private JRequestsLocationFrame requestsLocationFrame;
	
	public JInitialFrame() {
		super("Taxi Agent Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
//		roadLocationFrame = new JRoadLocationFrame(ambient, this);
//		agentLocationFrame = new JAgentLocationFrame(ambient, this);
//		requestsLocationFrame = new JRequestsLocationFrame(ambient, this);
		
		panelSize.add(labelRows);
		panelSize.add(fieldRows);
		panelSize.add(labelCols);
		panelSize.add(fieldCols);
		
		group.add(radioButtonKnown);
		group.add(radioButtonUnknown);
		
		panelRequest.add(radioButtonKnown);
		panelRequest.add(radioButtonUnknown);
		
		//panelInitInfo.add(labelTitle);
		panelInitInfo.add(labelSize);
		panelInitInfo.add(panelSize);
		panelInitInfo.add(labelRequestType);
		panelInitInfo.add(panelRequest);
		
		buttonOK.addActionListener(this);
		add(labelTitle, BorderLayout.NORTH);
		add(panelInitInfo, BorderLayout.CENTER);
		add(buttonOK, BorderLayout.SOUTH);
		//pack();
	}
	
	public JAmbient getAmbient() {
		return ambient;
	}

	public JRoadLocationFrame getRoadLocationFrame() {
		return roadLocationFrame;
	}

	public JAgentLocationFrame getAgentLocationFrame() {
		return agentLocationFrame;
	}

	public JRequestsLocationFrame getRequestsLocationFrame() {
		return requestsLocationFrame;
	}

	public void setAmbient(JAmbient ambient) {
		this.ambient = ambient;
	}

	public void setRoadLocationFrame(JRoadLocationFrame roadLocationFrame) {
		this.roadLocationFrame = roadLocationFrame;
	}

	public void setAgentLocationFrame(JAgentLocationFrame agentLocationFrame) {
		this.agentLocationFrame = agentLocationFrame;
	}

	public void setRequestsLocationFrame(JRequestsLocationFrame requestsLocationFrame) {
		this.requestsLocationFrame = requestsLocationFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == buttonOK) {
			String strrows = fieldRows.getText();
			String strcols = fieldCols.getText();
			int rows = Integer.parseInt(strrows);
			int cols = Integer.parseInt(strcols);
			System.out.printf("rows: %d, cols: %d\n", rows, cols);
			if(radioButtonKnown.isSelected()) {
				ambient = new JAmbient(JAmbient.SETTING_ROAD, rows, cols, this);
				this.setVisible(false);
				this.setRoadLocationFrame(new JRoadLocationFrame(ambient, this));
				this.getRoadLocationFrame().setVisible(true);
			}
		}
	}
	
	public static void main(String[] args) {
		JInitialFrame frame = new JInitialFrame();
		frame.setSize(300, 180);
		frame.setVisible(true);
	}

}

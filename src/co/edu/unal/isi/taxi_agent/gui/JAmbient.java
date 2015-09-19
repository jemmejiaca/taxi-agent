package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class JAmbient extends JPanel implements MouseListener {
	public static final int DISABLED = 0;
	public static final int SETTING_TAXI_AGENT = 1;
	public static final int SETTING_REQUESTS = 2;
	public static final int SETTING_DESTINY = 3;
	
	public static final Color DISABLED_COLOR = Color.WHITE;
	public static final Color ROAD_COLOR = Color.GRAY;
	public static final Color REQUEST_COLOR = Color.RED;
	public static final Color TAXI_AGENT_COLOR = Color.BLUE;
	public static final int GAP = 2;
	
	private int rows;
	private int cols;
	private int state;
	private JCell[][] grid; 
	JAgentLocationFrame agentLocationFrame;
	
	
	public JAmbient(int state, int rows, int cols, JAgentLocationFrame agentLocationFrame) {
		this.state = state;
		this.agentLocationFrame = agentLocationFrame;
		setLayout(new GridLayout(rows, cols, GAP, GAP));
		setBackground(Color.BLACK);
		this.rows = rows;
		this.cols = cols;
		grid = new JCell[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new JCell(state, 20, 20, this);
				grid[i][j].addMouseListener(this);
				grid[i][j].setCoordinate(i, j);
				add(grid[i][j]);
			}
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JPanel) {
			if(state == SETTING_TAXI_AGENT) {
				
				JCell selectedPanel = (JCell) source;
				int i = selectedPanel.getI();
				int j = selectedPanel.getJ();
				
				grid[i][j].setBackground(Color.RED);
				
				String strNumberOfRequests = JOptionPane.showInputDialog(
						this, "Please, enter the number of requests:");
				
				int numOfRequests = Integer.parseInt(strNumberOfRequests);
				System.out.println(numOfRequests);
				
				agentLocationFrame.setVisible(false);
				
				JRequestsLocationFrame requestLocationFrame = 
						new JRequestsLocationFrame(this, numOfRequests);
				
				requestLocationFrame.setVisible(true);
				requestLocationFrame.setSize(500, 500);
			}
			else if(state == SETTING_REQUESTS) {
				
			}
			
		}
		
	}
	
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

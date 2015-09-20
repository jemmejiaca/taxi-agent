package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.unal.isi.taxi_agent.model.Request;


public class JAmbient extends JPanel implements MouseListener {
	public static final int BLOCKED = 0;
	public static final int SETTING_ROAD = 1;
	public static final int SETTING_TAXI_AGENT = 2;
	public static final int SETTING_REQUESTS = 3;
	public static final int SETTING_DESTINY = 4;
	
	public static final Color DISABLED_COLOR = Color.WHITE;
	public static final Color ROAD_COLOR = Color.DARK_GRAY;
	public static final Color REQUEST_COLOR = Color.RED;
	public static final Color TAXI_AGENT_COLOR = Color.BLUE;
	public static final int GAP = 2;
	
	private int rows;
	private int cols;
	private int state;
	private JCell[][] grid;
	private ArrayList<JCell> road = new ArrayList<JCell>();
	private JInitialFrame initialFrame;
	
	public JAmbient(int state, int rows, int cols, JInitialFrame initialFrame) {
		this.state = state;
		this.initialFrame = initialFrame;
		setLayout(new GridLayout(rows, cols, GAP, GAP));
		setBackground(Color.BLACK);
		this.rows = rows;
		this.cols = cols;
		grid = new JCell[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new JCell(20, 20, this);
				grid[i][j].addMouseListener(this);
				grid[i][j].setCoordinate(i, j);
				add(grid[i][j]);
			}
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (state == SETTING_ROAD) {
			Object source = e.getSource();
			if (source instanceof JCell) {
				JCell selectedPanel = (JCell) source;
				int i = selectedPanel.getI();
				int j = selectedPanel.getJ();
				
				grid[i][j].setBackground(JAmbient.ROAD_COLOR);
				
			}
		}
		else if(state == SETTING_TAXI_AGENT) {
			Object source = e.getSource();
			if (source instanceof JCell) {
				
				JCell selectedPanel = (JCell) source;
				int i = selectedPanel.getI();
				int j = selectedPanel.getJ();
				
				grid[i][j].setBackground(Color.RED);
				setState(BLOCKED);
			}
		}
		else if(state == SETTING_REQUESTS) {
			int numOfRequests = 3;
			Request[] requests = new Request[numOfRequests];
			for (int i = 0; i < numOfRequests; i++) {
				JCell originCell = (JCell) e.getSource();
				int i_o = originCell.getI(), j_o = originCell.getJ();
				grid[i_o][j_o].setBackground(Color.BLUE);
				JCell destinyCell = (JCell) e.getSource();
				int i_f = destinyCell.getI(), j_f = destinyCell.getJ();
				grid[i_o][j_o].setBackground(Color.YELLOW);
				String strSlots = 
						JOptionPane.showInputDialog(this, "Enter the number of slots:");
				int slots = Integer.parseInt(strSlots);
				requests[i] = new Request(i_o, j_o, slots, i_f, j_f);
			}
		}
		
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}

	
}

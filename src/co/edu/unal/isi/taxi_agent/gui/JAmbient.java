package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.unal.isi.taxi_agent.logic.Request;

public class JAmbient extends JPanel implements MouseListener {
	public static final int BLOCKED = 0;
	public static final int SETTING_ROAD = 1;
	public static final int SETTING_TAXI_AGENT = 2;
	public static final int SETTING_REQUESTS = 3;
	public static final int SETTING_DESTINY = 4;

	public static final Color DISABLED_COLOR = Color.WHITE;
	public static final Color ROAD_COLOR = Color.DARK_GRAY;
	public static final Color ORIGIN_COLOR = Color.RED;
	public static final Color DESTINY_COLOR = Color.YELLOW;
	public static final Color TAXI_AGENT_COLOR = Color.BLUE;
	public static final int GAP = 2;
	public static final int ESTABLISHED_ORIGIN = 1;

	private int rows;
	private int cols;
	private int state, requestState;
	private JCell[][] grid;
	private ArrayList<JCell> road = new ArrayList<JCell>();
	private ArrayList<Request> requests = new ArrayList<Request>();
	private JInitialFrame initialFrame;
	

	public JAmbient(int state, int rows, int cols, JInitialFrame initialFrame) {
		this.state = state;
		this.initialFrame = initialFrame;
		setLayout(new GridLayout(rows, cols, GAP, GAP));
		setBackground(Color.BLACK);
		this.rows = rows;
		this.cols = cols;
		grid = new JCell[rows][cols];
		this.requestState = BLOCKED;
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
				road.add(grid[i][j]);

			}
		} else if (state == SETTING_TAXI_AGENT) {
			Object source = e.getSource();
			if (source instanceof JCell) {

				JCell selectedPanel = (JCell) source;
				int i = selectedPanel.getI();
				int j = selectedPanel.getJ();

				grid[i][j].setBackground(TAXI_AGENT_COLOR);
				setState(BLOCKED);
				
			}
		} else if (state == SETTING_REQUESTS) {
			int iOrigin = 0, jOrigin = 0, numOfPassengers, iFinal, jFinal;
			Object source = e.getSource();
			if (source instanceof JCell) {
				int i = ((JCell) source).getI(), j = ((JCell) source).getJ();
				if (grid[i][j].getBackground().equals(ROAD_COLOR) && requestState == BLOCKED) {
					grid[i][j].setBackground(ORIGIN_COLOR);
					requestState = ESTABLISHED_ORIGIN;
					iOrigin = i; jOrigin = j;
				}
				else if (grid[i][j].getBackground().equals(ROAD_COLOR) && requestState == ESTABLISHED_ORIGIN) {
					grid[i][j].setBackground(DESTINY_COLOR);
					requestState = BLOCKED;
					iFinal = i; jFinal = j;
					numOfPassengers = Integer.parseInt(
							JOptionPane.showInputDialog(
									this, "Enter the number of passengers:"));
					requests.add(new Request(iOrigin, jOrigin, numOfPassengers, iFinal, jFinal));
					System.out.printf("Added request: %d %d %d %d %d", iOrigin, jOrigin, numOfPassengers, iFinal, jFinal);
				}
			}
		}

	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

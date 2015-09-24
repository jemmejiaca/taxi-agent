package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.unal.isi.taxi_agent.logic.*;

public class JAmbient extends JPanel implements MouseListener {
	public static final int BLOCKED = 0;
	public static final int SETTING_ROAD = 1;
	public static final int SETTING_TAXI_AGENT = 2;
	public static final int SETTING_REQUESTS = 3;
	public static final int SETTING_DESTINY = 4;

	public static final Color DISABLED_COLOR = Color.WHITE;
	public static final Color ROAD_COLOR = Color.DARK_GRAY;
	public static final Color ORIGIN_COLOR = Color.BLUE;
	public static final Color DESTINY_COLOR = Color.RED;
	public static final Color TAXI_AGENT_COLOR = Color.YELLOW;
	public static final int ESTABLISHED_ORIGIN = 1;
	
	public static final int GAP = 2;

	private int rows, cols;
	private int state, requestState;
	private JCell[][] grid;
	private Situation situation;
	private JInitialFrame initialFrame;
	private Position startRequestPosition = new Position();;
	private Graph camino;

	public JAmbient(int state, int rows, int cols, JInitialFrame initialFrame) {
		this.rows = rows;
		this.cols = cols;
		this.state = state;
		this.requestState = BLOCKED;
		this.grid = new JCell[rows][cols];
		this.situation = new Situation();
		this.initialFrame = initialFrame;
		setLayout(new GridLayout(rows, cols, GAP, GAP));
		setBackground(Color.BLACK);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new JCell(20, 20, this);
				grid[i][j].addMouseListener(this);
				grid[i][j].setPosition(new Position(i, j));
				add(grid[i][j]);
			}
		}

		camino = new Graph(rows,cols);
		System.out.println(state);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (state == SETTING_ROAD) {
			RoadMap road = new RoadMap();
			Position selectedCellPosition;
			Object source = e.getSource();
			if (source instanceof JCell) {
				JCell selectedPanel = (JCell) source;
				selectedCellPosition = selectedPanel.getPosition();
				int i = selectedCellPosition.getI();
				int j = selectedCellPosition.getJ();
				grid[i][j].setBackground(JAmbient.ROAD_COLOR);
				road.addPosition(selectedCellPosition);
				situation.setInputRoad(road);
				
				camino.addEdge(i, j);

				
			}
		} else if (state == SETTING_TAXI_AGENT) {
			Object source = e.getSource();
			TaxiAgent taxiAgent = new TaxiAgent();
			Position selectedCellPosition;
			if (source instanceof JCell) {
				JCell selectedCell = (JCell) source;
				selectedCellPosition = selectedCell.getPosition();
				int i = selectedCellPosition.getI();
				int j = selectedCellPosition.getJ();
				grid[i][j].setBackground(TAXI_AGENT_COLOR);
				setState(BLOCKED);
				
				camino.getAgent().setPosition(selectedCellPosition);;
				camino.print();
			}
		} else if (state == SETTING_REQUESTS) {
			int numOfPassengers, iFinal, jFinal;
			Request request = new Request();
			Position selectedCellPosition;
			Object source = e.getSource();
			if (source instanceof JCell) {
				JCell selectedCell = (JCell) source;
				selectedCellPosition = selectedCell.getPosition();
				int i = selectedCellPosition.getI();
				int j = selectedCellPosition.getJ();
				startRequestPosition.setI(i);
				startRequestPosition.setJ(j);
				if (grid[i][j].getBackground().equals(ROAD_COLOR) 
						&& requestState == BLOCKED) {
					grid[i][j].setBackground(ORIGIN_COLOR);
					requestState = ESTABLISHED_ORIGIN;
					request.setStartPosition(startRequestPosition);
				}
				else if ((grid[i][j].getBackground().equals(ROAD_COLOR) || grid[i][j].getBackground().equals(DESTINY_COLOR)) && requestState == ESTABLISHED_ORIGIN) {
					grid[i][j].setBackground(DESTINY_COLOR);
					requestState = BLOCKED;
					numOfPassengers = Integer.parseInt(
							JOptionPane.showInputDialog(
									this, "Enter the number of passengers of this request:"));
					request.setEndPosition(new Position(i, j));
					request.setRequestedQuota(numOfPassengers);
					System.out.println(request);
					situation.getRequests().add(request);
					//es mas conveniente tener este ArrayList dentro de la clase Grafo
					//requests.add(new Request(indexIOrigen, indexJOrigen, numOfPassengers, iFinal, jFinal));
					//camino.getPeticiones().add(new Request(indexIOrigen, indexJOrigen, numOfPassengers, iFinal, jFinal));
					System.out.print(request);
				}
			}
		}
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public Graph getCamino()
	{
		return camino;
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

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getState() {
		return state;
	}

	public int getRequestState() {
		return requestState;
	}

	public JCell[][] getGrid() {
		return grid;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setRequestState(int requestState) {
		this.requestState = requestState;
	}

	public void setGrid(JCell[][] grid) {
		this.grid = grid;
	}
	
	

}

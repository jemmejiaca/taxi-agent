package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private Graph graph;
	Request tmpRequest = new Request();		// Just a temporal request

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

		graph = new Graph(rows, cols);
		//System.out.println(state);
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
				
				//en mi ejemplo hacer solo caso al grafo que construí
				graph.addEdge(i, j);

			}
		} else if (state == SETTING_TAXI_AGENT) {
			Object source = e.getSource();
			TaxiAgent taxiAgent = new TaxiAgent();
			Position selectedCellPosition;
			if (source instanceof JCell) {
				JCell selectedCell = (JCell) source;
				selectedCellPosition = selectedCell.getPosition();
				System.out.println("Agent setted at: " + selectedCellPosition);
				int i = selectedCellPosition.getI();
				int j = selectedCellPosition.getJ();
				grid[i][j].setBackground(TAXI_AGENT_COLOR); //pintar la casilla del taxi
				taxiAgent.setPosition(selectedCellPosition);
				situation.setTaxiAgent(taxiAgent);
				setState(BLOCKED);
				graph.getAgent().setPosition(selectedCellPosition); //mi taxi en el grafo
				graph.print();
				System.out.println(taxiAgent);
			}
		} 
		else if (state == SETTING_REQUESTS) 
		{
			int numOfPassengers;
			
			Position startPosition, endPosition;
			Object source = e.getSource();

			if (source instanceof JCell) {
				JCell selectedCell = (JCell) source;
				startPosition = selectedCell.getPosition();
				int startI = startPosition.getI();
				int startJ = startPosition.getJ();
				if (requestState == BLOCKED && grid[startI][startJ].getBackground().equals(ROAD_COLOR)) 
				{
					grid[startI][startJ].setBackground(ORIGIN_COLOR); //coloreando la celda de la peticion(donde está el pasajero)
					tmpRequest.setStartPosition(startPosition);
					requestState = ESTABLISHED_ORIGIN;
				}
				else if (requestState == ESTABLISHED_ORIGIN) 
				{
					endPosition = selectedCell.getPosition();
					int endI = endPosition.getI();
					int endJ = endPosition.getJ();
					if (grid[endI][endJ].getBackground().equals(ROAD_COLOR) || grid[endI][endJ].getBackground().equals(DESTINY_COLOR)) 
					{
						grid[endI][endJ].setBackground(DESTINY_COLOR); //coloreando la celda del destino(donde hay que llevar al pasajero)
						tmpRequest.setEndPosition(endPosition);
						System.out.println("end=" + grid[endI][endJ].getPosition());
						numOfPassengers = Integer.parseInt(
								JOptionPane.showInputDialog(this, "Enter the number"
										+ " of passengers of this request:"));
						tmpRequest.setRequestedQuota(numOfPassengers);
						situation.getRequests().add(tmpRequest);
						// Es mas conveniente tener este ArrayList dentro de la
						// clase Graph
						graph.getPeticiones().add(tmpRequest);
						
						System.out.println(tmpRequest);
						requestState = BLOCKED;
						
						//darle nueva memoria
						tmpRequest = new Request();
					}
				}
			}
		}
	}

	public void setState(int state) {
		this.state = state;
	}

	public Graph getCamino() {
		return graph;
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

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

}

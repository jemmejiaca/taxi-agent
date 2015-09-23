package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.unal.isi.taxi_agent.logic.Graph;
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
	
	private int indexIOrigen = 0;
	private int indexJOrigen = 0;
	
	private Graph camino;

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

		camino = new Graph(rows,cols);
		System.out.println(state);

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
				
				camino.addEdge(i, j);

				
			}
		} else if (state == SETTING_TAXI_AGENT) {
			Object source = e.getSource();
			if (source instanceof JCell) {

				JCell selectedPanel = (JCell) source;
				int i = selectedPanel.getI();
				int j = selectedPanel.getJ();

				grid[i][j].setBackground(TAXI_AGENT_COLOR);
				setState(BLOCKED);
				
				camino.getAgent().setPosicion(i, j);
				camino.print();
				
			}
		} else if (state == SETTING_REQUESTS) {
			int iOrigin = 0, jOrigin = 0 , numOfPassengers, iFinal, jFinal;
			Request request = new Request();
			Object source = e.getSource();
			if (source instanceof JCell) {
				int i = ((JCell) source).getI(), j = ((JCell) source).getJ();
				if (grid[i][j].getBackground().equals(ROAD_COLOR) 
						&& requestState == BLOCKED) {
					grid[i][j].setBackground(ORIGIN_COLOR);
					requestState = ESTABLISHED_ORIGIN;
					request.setOrigin(i, j);
					iOrigin = i; jOrigin = j;
					indexIOrigen = i; indexJOrigen = j;
				}
				else if ((grid[i][j].getBackground().equals(ROAD_COLOR) || grid[i][j].getBackground().equals(DESTINY_COLOR)) && requestState == ESTABLISHED_ORIGIN) {
					grid[i][j].setBackground(DESTINY_COLOR);
					requestState = BLOCKED;
					iFinal = i; jFinal = j;
					numOfPassengers = Integer.parseInt(
							JOptionPane.showInputDialog(
									this, "Enter the number of passengers:"));
					request.setFinal(i, j);
					request.setNumberOfPassengers(numOfPassengers);
					System.out.println(request);
					requests.add(request);
					//es mas conveniente tener este ArrayList dentro de la clase Grafo
					//requests.add(new Request(indexIOrigen, indexJOrigen, numOfPassengers, iFinal, jFinal));
					camino.getPeticiones().add(new Request(indexIOrigen, indexJOrigen, numOfPassengers, iFinal, jFinal));
					System.out.printf("Added request: %d %d %d %d %d", indexIOrigen, indexJOrigen, numOfPassengers, iFinal, jFinal);
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

}

package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class JAmbient extends JPanel {
	public static final int GAP = 2;
	private int rows;
	private int cols;
	private JCell[][] grid; 
	 //Component a =  this.getConte
	
	
	public JAmbient(int rows, int cols) {
		setLayout(new GridLayout(rows, cols, GAP, GAP));
		setBackground(Color.BLACK);
		this.rows = rows;
		this.cols = cols;
		grid = new JCell[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new JCell(JCell.DISABLED, 20, 20, this);
				add(grid[i][j]);
			}
		}
		
		
	}

}

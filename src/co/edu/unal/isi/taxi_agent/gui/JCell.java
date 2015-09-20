package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class JCell extends JPanel {

	private int width, height;
	private int i, j;
	JAmbient ambient;
	
	public JCell(int width, int height, JAmbient ambient) {
		this.width = width;
		this.height = height;
		this.ambient = ambient;
		setSize(this.width, this.height);
	}
	
	public void setCoordinate(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}

}

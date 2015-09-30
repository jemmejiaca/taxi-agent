package co.edu.unal.isi.taxi_agent.gui;

import javax.swing.JPanel;

import co.edu.unal.isi.taxi_agent.logic.Position;

public class JCell extends JPanel {

	private int width, height;
	private Position position;
	JAmbient ambient;
	
	public JCell(int width, int height, JAmbient ambient) {
		this.width = width;
		this.height = height;
		this.ambient = ambient;
		setSize(this.width, this.height);
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

}

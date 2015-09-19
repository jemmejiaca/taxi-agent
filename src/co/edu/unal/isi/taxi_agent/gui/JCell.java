package co.edu.unal.isi.taxi_agent.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class JCell extends JPanel {

	private int width, height;
	private int i, j;
	//private Color color;
	private int state;
	JAmbient ambient;
	
	public JCell(int state, int width, int height, JAmbient ambient) {
		//setColor(DISABLED_COLOR);
		this.state = state;
		this.width = width;
		this.height = height;
		this.ambient = ambient;
		//addMouseListener(this);
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
	
	/**
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (state) {
		case SETTING_TAXI_AGENT:
			setColor(TAXI_AGENT_COLOR);
			break;
		case SETTING_REQUESTS:
			setColor(REQUEST_COLOR);
			break;
		case SETTING_DESTINY:
			setColor(REQUEST_COLOR);
		default:
			setColor(DISABLED_COLOR);
			break;
		}
	}
	
	public void setColor(Color color) {
		this.setBackground(color);
	}
	
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) {	}
	**/
}

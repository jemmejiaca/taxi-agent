package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;

public class RoadMap {
	private ArrayList<Position> positions;
	
	public RoadMap(ArrayList<Position> positions) {
		this.positions = positions;
	}
	
	public RoadMap() {
		positions = new ArrayList<Position>();
	}

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}
	
	public void addPosition(Position position) {
		this.positions.add(position);
	}
	
	public void deletePosition(Position position) {
		this.positions.remove(position);
	}
	
	public int getSize() {
		return this.positions.size() - 1;
	}

}

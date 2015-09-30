package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;

import co.edu.unal.isi.taxi_agent.gui.JAmbient;

public class TaxiAgent {
	static final int MAX_QUOTA = 12;
	private Position position;
	private int quota;
	private JAmbient ambient;
	private ArrayList<Position> traveledPath;

	public TaxiAgent(Position position, int quota, JAmbient ambient) {
		this.position = position;
		this.quota = quota;
		this.ambient = ambient;
		this.traveledPath = new ArrayList<>();
	}
	
	public JAmbient getAmbient() {
		return ambient;
	}

	public void setAmbient(JAmbient ambient) {
		this.ambient = ambient;
	}

	public TaxiAgent() {
		position = new Position();
		quota = 0;
		this.traveledPath = new ArrayList<>();
	}
	
	public Position getPosition() {
		return position;
	}

	public int getQuota() {
		return quota;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	public ArrayList<Position> getNeighborPositions() {
		int i = this.getPosition().getI();
		int j = this.getPosition().getJ();
		final int MAX_I = ambient.getRows();
		final int MAX_J = ambient.getCols();
		ArrayList<Position> neighbors = new ArrayList<>();
		if (i - 1 >= 0) neighbors.add(new Position(i - 1, j));
		if (j - 1 >= 0) neighbors.add(new Position(i, j - 1));
		if (i + 1 < MAX_I) neighbors.add(new Position(i + 1, j));
		if (j + 1 < MAX_J) neighbors.add(new Position(i, j + 1));
		for (Position p : neighbors)
			System.out.println(p);
		return neighbors;
	}

	public int getRemainingQuota() {
		return MAX_QUOTA - quota;
	}

	@Override
	public String toString() {
		return "TaxiAgent [position=" + position + ", quota=" + quota + "]";
	}
	
	public ArrayList<Position> getTraveledPath() {
		return traveledPath;
	}

	public void setTraveledPath(ArrayList<Position> traveledPath) {
		this.traveledPath = traveledPath;
	}
	
	public void addTraveledPosition(Position traveledPosition) {
		traveledPath.add(traveledPosition);
	}

}

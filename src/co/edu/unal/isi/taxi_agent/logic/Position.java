package co.edu.unal.isi.taxi_agent.logic;

public class Position {
	private int i, j;
	private boolean isRoad;
	private boolean isStartRequest;
	private boolean isEndRequest;
	
	public boolean isRoad() {
		return isRoad;
	}

	public boolean isStartRequest() {
		return isStartRequest;
	}

	public boolean isEndRequest() {
		return isEndRequest;
	}

	public void setRoad(boolean isRoad) {
		this.isRoad = isRoad;
	}

	public void setStartRequest(boolean isStartRequest) {
		this.isStartRequest = isStartRequest;
	}

	public void setEndRequest(boolean isEndRequest) {
		this.isEndRequest = isEndRequest;
	}

	public Position(int x, int y) {
		i = x; j = y;
	}
	
	public Position() {
		i = 0; j = 0;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "(" + i + ", " + j + ")\n" +
				isRoad + ", " + isStartRequest + ", " + isEndRequest();
	}
	
	

}

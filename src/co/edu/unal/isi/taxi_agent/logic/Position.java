package co.edu.unal.isi.taxi_agent.logic;

public class Position {
	private int i, j;
	
	public Position(int x, int y) {
		i = x; j = y;
	}
	
	public Position() {
		i = -1; j = -1;
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
		return "(" + i + ", " + j + ")";
	}
	
	

}

package co.edu.unal.isi.taxi_agent.logic;

public class TaxiAgent {
	static final int MAX_QUOTA = 12;
	private Position position;
	private int quota;
	
	public TaxiAgent() { }
	
	public TaxiAgent(Position position, int quota) {
		this.position = position;
		this.quota = quota;
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

	public int getRemainingQuota() {
		return MAX_QUOTA - quota;
	}


}

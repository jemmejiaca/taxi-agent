package co.edu.unal.isi.taxi_agent.logic;

public class TaxiAgent {
	static final int MAX_QUOTA = 12;
	private Position position;
	private int quota;
	
	public TaxiAgent(Position position, int quota) {
		this.position = position;
		this.quota = quota;
	}
	
	public TaxiAgent() {
		position = new Position();
		quota = 0;
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
	
	@Override
	public String toString()
	{
		return "Agent Position: ("+position.getI()+","+position.getJ()+")";
	}
}

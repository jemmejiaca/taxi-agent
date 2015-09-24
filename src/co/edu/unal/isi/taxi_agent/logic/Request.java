package co.edu.unal.isi.taxi_agent.logic;

public class Request {
	private Position startPosition;
	private int requestedQuota;
	private Position endPosition;
	
	public Request(Position startPosition, int quota, Position endPosition) {
		this.startPosition = startPosition;
		this.requestedQuota = quota;
		this.endPosition = endPosition;
	}
	
	public Request() {
		// TODO Auto-generated constructor stub
	}

	public Position getStartPosition() {
		return startPosition;
	}

	public int getRequestedQuota() {
		return requestedQuota;
	}

	public Position getEndPosition() {
		return endPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public void setRequestedQuota(int quota) {
		this.requestedQuota = quota;
	}

	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
	}

	@Override
	public String toString() {
		return "Request [startPosition=" + startPosition + ", requestedQuota=" + requestedQuota + ", endPosition=" + endPosition + "]";
	}


	
	
	
}
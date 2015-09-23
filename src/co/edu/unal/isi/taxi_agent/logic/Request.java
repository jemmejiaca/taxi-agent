package co.edu.unal.isi.taxi_agent.logic;

public class Request {
	private int iOrigin, jOrigin;
	private int numOfPassengers;
	private int iFinal, jFinal;
	
	public Request(int iOrigin, int jOrigin, int numOfPassengers, int iFinal, int jFinal) {
		this.iOrigin = iOrigin;
		this.jOrigin = jFinal;
		this.numOfPassengers = numOfPassengers;
		this.iFinal = iFinal;
		this.jFinal = jFinal;
	}
	
	public Request() {
		// TODO Auto-generated constructor stub
	}

	public int getIOrigin() {
		return iOrigin;
	}

	public int getJOrigin() {
		return jOrigin;
	}

	public int getNumberOfPassengers() {
		return numOfPassengers;
	}

	public int getIFinal() {
		return iFinal;
	}

	public int getJFinal() {
		return jFinal;
	}

	public void setIOrigin(int iOrigin) {
		this.iOrigin = iOrigin;
	}

	public void setJOrigin(int jOrigin) {
		this.jOrigin = jOrigin;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numOfPassengers = numberOfPassengers;
	}

	public void setIFinal(int iFinal) {
		this.iFinal = iFinal;
	}

	public void setJFinal(int jFinal) {
		this.jFinal = jFinal;
	}
	
	public void setOrigin(int iOrigin, int jOrigin) {
		this.iOrigin = iOrigin;
		this.jOrigin = jOrigin;
	}
	
	public void setFinal(int iFinal, int jFinal) {
		this.iFinal = iFinal;
		this.jFinal = jFinal;
	}

	@Override
	public String toString() {
		return "Request [iOrigin=" + iOrigin + ", jOrigin=" + jOrigin + ", numOfPassengers=" + numOfPassengers
				+ ", iFinal=" + iFinal + ", jFinal=" + jFinal + "]";
	}
	
	
	
}
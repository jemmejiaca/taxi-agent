package co.edu.unal.isi.taxi_agent.model;

public class Request {
	private int i_initial, j_initial;
	private int slots;
	private int i_final, j_final;
	
	public Request(int i_initial, int j_initial, int slots, int i_final, int j_final) {
		this.i_initial = i_initial;
		this.j_initial = j_initial;
		this.slots = slots;
		this.i_final = i_final;
		this.j_final = j_final;
	}

	public int getI_initial() {
		return i_initial;
	}

	public int getJ_initial() {
		return j_initial;
	}

	public int getNumberOfPassengers() {
		return slots;
	}

	public int getI_final() {
		return i_final;
	}

	public int getJ_final() {
		return j_final;
	}

	public void setI_initial(int i_initial) {
		this.i_initial = i_initial;
	}

	public void setJ_initial(int j_initial) {
		this.j_initial = j_initial;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.slots = numberOfPassengers;
	}

	public void setI_final(int i_final) {
		this.i_final = i_final;
	}

	public void setJ_final(int j_final) {
		this.j_final = j_final;
	}
	
	
	

}

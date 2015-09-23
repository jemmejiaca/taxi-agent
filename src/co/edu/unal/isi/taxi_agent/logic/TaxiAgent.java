package co.edu.unal.isi.taxi_agent.logic;

public class TaxiAgent 
{
	private int PosI;
	private int PosJ;
	private int cupoActual = 0;
	static final int CUPO = 4;
	
	public TaxiAgent(){}
	
	public TaxiAgent(int posI, int posJ) {
		PosI = posI;
		PosJ = posJ;
		cupoActual = 0;
	}

	public int getPosI() {
		return PosI;
	}
	
	public void setPosI(int posI) {
		PosI = posI;
	}

	public int getPosJ() {
		return PosJ;
	}

	public void setPosJ(int posJ) {
		PosJ = posJ;
	}
	
	public void setPosicion(int posI,int posJ) {
		PosI = posI;
		PosJ = posJ;
	}

	public int getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(int cupoActual) {
		this.cupoActual = cupoActual;
	} 

}

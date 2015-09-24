package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;

import co.edu.unal.isi.taxi_agent.logic.RoadMap;
import co.edu.unal.isi.taxi_agent.logic.TaxiAgent;

public class Situation {
	private RoadMap inputRoad;
	private TaxiAgent taxiAgent;
	private ArrayList<Request> requests;
	
	public Situation(RoadMap inputRoad, TaxiAgent taxiAgent, ArrayList<Request> requests) {
		this.inputRoad = inputRoad;
		this.taxiAgent = taxiAgent;
		this.requests = requests;
	}
	
	public Situation() {
		this.inputRoad = null;
		this.taxiAgent = null;
		this.requests = null;
	}

	public RoadMap getInputRoad() {
		return inputRoad;
	}

	public TaxiAgent getTaxiAgent() {
		return taxiAgent;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setInputRoad(RoadMap inputRoad) {
		this.inputRoad = inputRoad;
	}

	public void setTaxiAgent(TaxiAgent taxiAgent) {
		this.taxiAgent = taxiAgent;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	
	
}
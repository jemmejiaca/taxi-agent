package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;
import java.util.Random;

import com.sun.corba.se.impl.io.OutputStreamHook;

import co.edu.unal.isi.taxi_agent.logic.RoadMap;
import co.edu.unal.isi.taxi_agent.logic.TaxiAgent;

public class SituationSolver {
	private RoadMap road;
	private TaxiAgent agent;
	private ArrayList<Request> requests;

	public SituationSolver(RoadMap road, TaxiAgent agent, ArrayList<Request> requests) {
		this.road = road;
		this.agent = agent;
		this.requests = requests;
	}

	public SituationSolver() {
		road = new RoadMap();
		agent = new TaxiAgent();
		requests = new ArrayList<Request>();
	}

	public ArrayList<Position> algorithm01() {
		ArrayList<Request> inputRequests = requests;
		ArrayList<Position> outputPath = new ArrayList<>();
		ArrayList<Position> roadPositions = getRoad().getPositions();
		while (!inputRequests.isEmpty()) {
			if (agent.getPosition().isRoad()) { /* Deal with a road block. */
				ArrayList<Position> neighbors = agent.getNeighborPositions();
				ArrayList<Position> factiblePositions = new ArrayList<>();
				for (Position position : neighbors)
					if (position.isRoad() && !agent.getTraveledPath().contains(position))
						factiblePositions.add(position);
				if (factiblePositions.size() == 1) {
					Position next = factiblePositions.get(0);	// Drop out next and idx.
					agent.setPosition(next);
					agent.addTraveledPosition(next);
					outputPath.add(next);
				} else {
					int idx = new Random().nextInt(factiblePositions.size());
					Position next = factiblePositions.get(idx);
					agent.setPosition(next);
					agent.addTraveledPosition(next);
					outputPath.add(next);
				}
			} else if (agent.getPosition().isStartRequest()) { /* Deal with a start request block */
				
			} else if (agent.getPosition().isEndRequest()) { /* Deal with an end request block */
				
			} 

		}
		return outputPath;
	}

	public RoadMap getRoad() {
		return road;
	}

	public TaxiAgent getAgent() {
		return agent;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRoad(RoadMap road) {
		this.road = road;
	}

	public void setAgent(TaxiAgent agent) {
		this.agent = agent;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}

}
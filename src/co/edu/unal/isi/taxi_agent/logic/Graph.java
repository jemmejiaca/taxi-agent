package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph 
{	
	private int rows;
	private int cols;	
	private int grafo[][];
	private TaxiAgent agent;
	private ArrayList<Request> peticiones;
	private Map<Position,Position> padres;
	
	public Graph(int rows, int  cols)
	{
		this.rows = rows;
		this.cols = cols;
		grafo = new int[rows][cols];
		peticiones = new ArrayList<>();
		agent = new TaxiAgent();
		padres = new HashMap<>();
				
		for(int i = 0; i < rows;i++){
			for (int j = 0; j < cols; j++){
				grafo[i][j] = 0;
			}
		}
	}
	
	public Graph(int nroVertices)
	{
		this(nroVertices, nroVertices);
	}
	
	public Graph(){}
	
	public void addEdge(int row, int col)
	{
		//controlar excepciones
		grafo[row][col] = 1;
	}

	public TaxiAgent getAgent() {
		return agent;
	}

	public void setAgent(TaxiAgent agent) {
		this.agent = agent;
	}

	public ArrayList<Request> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(ArrayList<Request> peticiones) {
		this.peticiones = peticiones;
	}
	
	public Map<Position, Position> getPadres() {
		return padres;
	}

	public void setPadres(Map<Position, Position> padres) {
		this.padres = padres;
	}
	
	public void cleanPadres()
	{
		this.padres.clear();
	}

	public void print() {
			System.out.print("    ");
			for(int i = 0; i < this.cols; i++) {
				System.out.printf(" %3d", i);
			}
			System.out.println();
			for( int i = 0; i < this.rows; i++) {
				System.out.printf(" %3d", i);
				for(int j = 0; j < this.cols; j++) {
					System.out.printf(" %3d" , grafo[i][j]);
				}
				System.out.println();
			}
	}
	
	public void searchingPetition(Request primeraPeticion)
	{
		int aux=0;
		boolean visit[][] = new boolean[this.rows][this.cols];
		
		int iTaxi = agent.getPosition().getI(), jTaxi = agent.getPosition().getJ(); 
		
		for(int i=0;i<this.rows;i++)
		{
			for(int j=0;j<this.cols;j++)
			{
				visit[i][j] = false;
			}
			
		}
		visit[iTaxi][jTaxi] = true;
		
		LinkedList<Integer> colaRow = new LinkedList<>();
		LinkedList<Integer> colaCol = new LinkedList<>();
		
		colaRow.addLast(iTaxi);
		colaCol.addLast(jTaxi);
		
		while(!(colaRow.isEmpty()) && !(colaCol.isEmpty()))
		{
			int i = colaRow.removeFirst();
			int j = colaCol.removeFirst();
			
			if (i-1>=0) 
			{
				if (visit[i-1][j]==false && grafo[i-1][j]==1) 
				{ 
					visit[i-1][j]=true;					
					colaRow.addLast(i-1);
					colaCol.addLast(j);
							
					padres.put(new Position(i-1,j),new Position(i,j));
					
					if(primeraPeticion.getStartPosition().getI() == i-1 &&
							primeraPeticion.getStartPosition().getJ() == j)
					{
						//agent.getPosition().setI(i-1);
						//agent.getPosition().setI(j);
						aux=1;
						break;
					}
				}
			}
			
			if (i+1<rows) {
				if (visit[i+1][j]==false && grafo[i+1][j]==1) { 
					visit[i+1][j]=true;				
					colaRow.addLast(i+1);
					colaCol.addLast(j);
					
					padres.put(new Position(i+1,j),new Position(i,j));
					
					if(primeraPeticion.getStartPosition().getI() == i+1 &&
							primeraPeticion.getStartPosition().getJ() == j)
					{
						//agent.getPosition().setI(i+1);
						//agent.getPosition().setI(j);
						aux=1;
						break;
					}
				}
			}
			
			if (j-1>=0) {
				if (visit[i][j-1]==false && grafo[i][j-1]==1) { 
					visit[i][j-1]=true;					
					colaRow.addLast(i);
					colaCol.addLast(j-1);
					
					padres.put(new Position(i,j-1),new Position(i,j));
					
					if(primeraPeticion.getStartPosition().getI() == i &&
							primeraPeticion.getStartPosition().getJ() == j-1)
					{
						//agent.getPosition().setI(i);
						//agent.getPosition().setI(j-1);
						aux=1;
						break;
					}
				}
			}
			
			if (j+1<cols) {
				if (visit[i][j+1]==false && grafo[i][j+1]==1) { 
					visit[i][j+1]=true;
					
					colaRow.addLast(i);
					colaCol.addLast(j+1);
					
					padres.put(new Position(i,j+1),new Position(i,j));
					
					if(primeraPeticion.getStartPosition().getI() == i &&
							primeraPeticion.getStartPosition().getJ() == j+1)
					{
						//agent.getPosition().setI(i);
						//agent.getPosition().setI(j+1);
						aux=1;
						break;
					}
				}
			}
		}
		
		if(aux == 1)
		{
			int newi = primeraPeticion.getStartPosition().getI();
			int newj = primeraPeticion.getStartPosition().getJ();
			
			//System.out.println("New Agent position: "+newi+" "+newj);
			
			agent.sumQuota(primeraPeticion.getRequestedQuota());
			
			agent.getPosition().setI(newi);
			agent.getPosition().setJ(newj);
			
			//System.out.println(agent + "antes de salir del grafo");
		}
		
	}
	
	public void searchingDestinyPetition(Request primeraPeticion)
	{
		int aux=0;
		boolean visit[][] = new boolean[this.rows][this.cols];
		
		int iTaxi = agent.getPosition().getI(), jTaxi = agent.getPosition().getJ(); 
		
		for(int i=0;i<this.rows;i++)
		{
			for(int j=0;j<this.cols;j++)
			{
				visit[i][j] = false;
			}
			
		}
		visit[iTaxi][jTaxi] = true;
		
		LinkedList<Integer> colaRow = new LinkedList<>();
		LinkedList<Integer> colaCol = new LinkedList<>();
		
		colaRow.addLast(iTaxi);
		colaCol.addLast(jTaxi);
		
		while(!(colaRow.isEmpty()) && !(colaCol.isEmpty()))
		{
			int i = colaRow.removeFirst();
			int j = colaCol.removeFirst();
			
			if (i-1>=0) 
			{
				if (visit[i-1][j]==false && grafo[i-1][j]==1) 
				{ 
					visit[i-1][j]=true;					
					colaRow.addLast(i-1);
					colaCol.addLast(j);
					
					padres.put(new Position(i-1,j),new Position(i,j));
					
					if(primeraPeticion.getEndPosition().getI() == i-1 &&
							primeraPeticion.getEndPosition().getJ() == j)
					{
						//agent.getPosition().setI(i-1);
						//agent.getPosition().setI(j);
						aux = 1;
						break;
					}
				}
			}
			
			if (i+1<rows) {
				if (visit[i+1][j]==false && grafo[i+1][j]==1) { 
					visit[i+1][j]=true;				
					colaRow.addLast(i+1);
					colaCol.addLast(j);
					
					padres.put(new Position(i+1,j),new Position(i,j));
					
					if(primeraPeticion.getEndPosition().getI() == i+1 &&
							primeraPeticion.getEndPosition().getJ() == j)
					{
						//agent.getPosition().setI(i+1);
						//agent.getPosition().setI(j);
						aux = 1;
						break;
					}
				}
			}
			
			if (j-1>=0) {
				if (visit[i][j-1]==false && grafo[i][j-1]==1) { 
					visit[i][j-1]=true;					
					colaRow.addLast(i);
					colaCol.addLast(j-1);
					
					padres.put(new Position(i,j-1),new Position(i,j));
					
					if(primeraPeticion.getEndPosition().getI() == i &&
							primeraPeticion.getEndPosition().getJ() == j-1)
					{
						//agent.getPosition().setI(i);
						//agent.getPosition().setI(j-1);
						aux = 1;
						break;
					}
				}
			}
			
			if (j+1<cols) {
				if (visit[i][j+1]==false && grafo[i][j+1]==1) { 
					visit[i][j+1]=true;
					
					colaRow.addLast(i);
					colaCol.addLast(j+1);
					
					padres.put(new Position(i,j+1),new Position(i,j));
					
					if(primeraPeticion.getEndPosition().getI() == i &&
							primeraPeticion.getEndPosition().getJ() == j+1)
					{
						//agent.getPosition().setI(i);
						//agent.getPosition().setI(j+1);
						aux = 1;
						break;
					}
				}
			}
		}
		
		if(aux == 1)
		{
			int newi = primeraPeticion.getEndPosition().getI();
			int newj = primeraPeticion.getEndPosition().getJ();
			
			//System.out.println("New Agent position: "+newi+" "+newj);
			
			agent.restQuota(primeraPeticion.getRequestedQuota());
			
			agent.getPosition().setI(newi);
			agent.getPosition().setJ(newj);
			
			//System.out.println(agent + "antes de salir del grafo");
		}
		
	}
	
}

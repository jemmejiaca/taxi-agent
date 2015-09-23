package co.edu.unal.isi.taxi_agent.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph 
{
	private int state = 0; //cero, buscando carrera, 1, llevar a destino 
	
	private int rows;
	private int cols;	
	private int grafo[][];
	private Map<int[],int[]> padres;
	private TaxiAgent agent;
	private ArrayList<Request> peticiones;
	
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
	
	public Map<int[], int[]> getPadres() {
		return padres;
	}

	public void setPadres(Map<int[], int[]> padres) {
		this.padres = padres;
	}

	public void print()
	{
			System.out.print(" ");
			for(int i = 0; i < this.cols; i++)
			{
				System.out.printf("  %3d", i);
			}
			System.out.println();
			for( int i = 0; i < this.rows; i++){
				System.out.printf(" %3d", i);
				for(int j = 0; j < this.cols; j++){
					System.out.printf(" %3d" ,grafo[i][j]);
				}
				System.out.println();
			}
	}
	
	public void shortestPath()
	{
		int tmp = 0;
		
		boolean visit[][] = new boolean[this.rows][this.cols];
		
		int iTaxi = agent.getPosI(), jTaxi = agent.getPosJ(); 
		
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
			
			if (i==peticiones.get(0).getIOrigin() && j==peticiones.get(0).getJOrigin())
			{
				if (state==0)
				{
					tmp = 1;
					break;
				}
				else
				{
					//si hay cupo y van para el mismo lado, continue
				}
			}
			
			if (i==peticiones.get(0).getIFinal() && j==peticiones.get(0).getJFinal() && state==1)
			{
				//vaciar cupo del taxi
				tmp = 2;
				break;
			}
			
			if (i-1>=0) {
				if (visit[i-1][j]==false) { 
					visit[i-1][j]=true;
					visit[j][i-1]=true;
					colaRow.addLast(i-1);
					colaCol.addLast(j);
					
					int father[] = {i,j};
					int son[] = {i-1,j};
							
					padres.put(son,father);
				}
			}
			
			if (i+1<rows) {
				if (visit[i+1][j]==false) { 
					visit[i+1][j]=true;
					visit[j][i+1]=true;
					colaRow.addLast(i+1);
					colaCol.addLast(j);
					
					int father[] = {i,j};
					int son[] = {i+1,j};
							
					padres.put(son,father);
				}
			}
			
			if (j-1>=0) {
				if (visit[i][j-1]==false) { 
					visit[i][j-1]=true;
					visit[j-1][i]=true;
					colaRow.addLast(i);
					colaCol.addLast(j-1);
					
					int father[] = {i,j};
					int son[] = {i,j-1};
							
					padres.put(son,father);
				}
			}
			
			if (j+1<cols) {
				if (visit[i][j+1]==false) { 
					visit[i][j+1]=true;
					visit[j+1][i]=true;
					colaRow.addLast(i);
					colaCol.addLast(j+1);
					
					int father[] = {i,j};
					int son[] = {i,j+1};
							
					padres.put(son,father);
				}
			}
		}
		
		
		if (tmp == 1)
		{
			tmp = 0;
			if(!(peticiones.isEmpty()))
			{
				state = 1;
				agent.setCupoActual(TaxiAgent.CUPO-peticiones.get(0).getNumberOfPassengers());
				agent.setPosI(peticiones.get(0).getIOrigin());
				agent.setPosJ(peticiones.get(0).getJOrigin());
				shortestPath();
			}
		}
		else if(tmp==2)
		{
			tmp = 0;
			state = 0;
			agent.setCupoActual(TaxiAgent.CUPO);
			agent.setPosI(peticiones.get(0).getIFinal());
			agent.setPosJ(peticiones.get(0).getJFinal());
			peticiones.remove(0);
			if(!(peticiones.isEmpty()))
			{
				shortestPath();
			}	
		}
		
	}
}

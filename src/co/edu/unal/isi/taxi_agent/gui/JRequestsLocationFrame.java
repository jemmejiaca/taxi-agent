package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import co.edu.unal.isi.taxi_agent.logic.Position;
import co.edu.unal.isi.taxi_agent.logic.TaxiAgent;

public class JRequestsLocationFrame extends JFrame implements ActionListener {
	private JLabel labelSelectRequests = new JLabel("Please select the requests:");
	private JAmbient ambient;
	private JButton buttonOK = new JButton("OK");
	private JInitialFrame initialFrame;
	
	public JRequestsLocationFrame(JAmbient ambient, JInitialFrame initialFrame) {
		super("Requests selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.initialFrame = initialFrame;
		
		buttonOK.addActionListener(this);
		this.ambient = ambient;
		this.ambient.setState(JAmbient.SETTING_REQUESTS);
		
		
		add(labelSelectRequests, BorderLayout.NORTH);
		add(ambient, BorderLayout.CENTER);
		add(buttonOK, BorderLayout.SOUTH);
	
		setSize(500, 500);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public void delayASecond()
	{
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		    System.out.println("Esperando...");
		    
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOK) {
			JOptionPane.showMessageDialog(this, "That's all, let's work on it!");
			
			//Para ordenar los caminos
			ArrayList<Position> theWay = new ArrayList<>();
			int careerOntoWay = 0;

			for (int i=0;i<ambient.getCamino().getPeticiones().size();i++)
			{
				if (careerOntoWay == 1)
				{
					careerOntoWay = 0;
					continue;
				}
				//Position agentpos = ambient.getCamino().getAgent().getPosition();
				int tmpi = new Integer(ambient.getCamino().getPeticiones().get(i).getStartPosition().getI());
				int tmpj = new Integer(ambient.getCamino().getPeticiones().get(i).getStartPosition().getJ());
				Position tmp = new Position(tmpi,tmpj);
				
				//buscar
				//ambient.getCamino().searchingPetition(ambient.getSituation().getRequests().get(i));			
				ambient.getCamino().searchingPetition(ambient.getCamino().getPeticiones().get(i));
				
				theWay.add(tmp);
				
				Position value;
				
				while(true)
				{
					value = ambient.getCamino().getPadres().get(tmp);
					if(ambient.getCamino().getPadres().containsKey(tmp))
					{
						//System.out.println("Si contiene la clave");
						theWay.add(value);	
					}else{
						//System.out.println("No contiene la clave");
						break;
					}
					
					/*if(value.equals(agentpos))
					{
						break;
					}*/
					
					tmp.setI(value.getI());
					tmp.setJ(value.getJ());
					
				} 
				
				theWay.remove(0);
				theWay.add(0, ambient.getCamino().getPeticiones().get(i).getStartPosition());
				System.out.println(theWay);
				
				/* esto funciona
				
				Iterator it = ambient.getCamino().getPadres().keySet().iterator();
				while(it.hasNext()){
				  int[] key = (int[]) it.next();
				  int value[] = ambient.getCamino().getPadres().get(key);
				  System.out.println("Clave: " + key[0]+" "+key[1] + " -> Valor: " + value[0]+" "+value[1]);
				} */
				
				
				//pintar camino
				for (int k = theWay.size()-1; k >= 0;k--)
				{
					int celdaI = theWay.get(k).getI();
					int celdaJ = theWay.get(k).getJ();
					//delay del programa
					delayASecond();
					ambient.getGrid()[celdaI][celdaJ].setBackground(JAmbient.TAXI_AGENT_COLOR);
				}
				
				//si en el recorrido encuentra a alguien
				for (int k = 0; k < theWay.size();k++)
				{
					if(i+1<ambient.getCamino().getPeticiones().size())
					{
						if(theWay.get(k).getI() == ambient.getCamino().getPeticiones().get(i+1).getStartPosition().getI() && 
								theWay.get(k).getJ() == ambient.getCamino().getPeticiones().get(i+1).getStartPosition().getJ())
						{
							int tmpPasajeros = ambient.getCamino().getAgent().getQuota() + ambient.getCamino().getPeticiones().get(i+1).getRequestedQuota();
							if(tmpPasajeros < TaxiAgent.MAX_QUOTA)
							{
								if( 	(
										ambient.getCamino().getPeticiones().get(i+1).getEndPosition().getJ() == ambient.getCamino().getPeticiones().get(i).getEndPosition().getJ() &&
										ambient.getCamino().getPeticiones().get(i+1).getEndPosition().getI() == ambient.getCamino().getPeticiones().get(i).getEndPosition().getI()
										)
								   )
								{
									//agregar la cuota y hacer la búsqueda
									careerOntoWay = 1;
									ambient.getCamino().getAgent().restQuota(ambient.getCamino().getAgent().getQuota());
									ambient.getCamino().getAgent().sumQuota(tmpPasajeros);
									JOptionPane.showMessageDialog(null, "Se ha recogido a un pasajero en el camino", "Llegando al origen", 1);
									System.out.println("Cuota actual del agente: "+String.valueOf(ambient.getCamino().getAgent().getQuota()));
								}
							
							}
						}
					}
				}
				
				
				//limpiar el diccionario y el arreglo auxiliar de caminos
				ambient.getCamino().cleanPadres();
				theWay.clear();
				
				//ademas comprobar el agente y el destino
				
				System.out.println(ambient.getCamino().getAgent());
				
				
				JOptionPane.showMessageDialog(null, "El agente se desplazó hacia el origen", "Llegando al origen", 1);
				
				
				//Empezar la segunda búsqueda
				int tmpi2 = new Integer(ambient.getCamino().getPeticiones().get(i).getEndPosition().getI());
				int tmpj2 = new Integer(ambient.getCamino().getPeticiones().get(i).getEndPosition().getJ());
				Position tmp2 = new Position(tmpi2,tmpj2);
				
				//ambient.getCamino().searchingDestinyPetition(ambient.getCamino().getPeticiones().get(i));
				ambient.getCamino().searchingDestinyPetition(ambient.getCamino().getPeticiones().get(i));
				//recuperar el diccionario y pintar camino
				
				theWay.add(tmp2);
				
				Position value2;
				
				while(true)
				{
					value2 = ambient.getCamino().getPadres().get(tmp2);
					if(ambient.getCamino().getPadres().containsKey(tmp2))
					{
						//System.out.println("Si contiene la clave");
						theWay.add(value2);	
					}else{
						//System.out.println("No contiene la clave");
						break;
					}
					
					tmp2.setI(value2.getI());
					tmp2.setJ(value2.getJ());
					
				} 
				
				theWay.remove(0);
				theWay.add(0,ambient.getCamino().getPeticiones().get(i).getEndPosition());
				System.out.println(theWay);
				
				//pintar camino
				for (int k = theWay.size()-1; k >= 0;k--)
				{
					int celdaI = theWay.get(k).getI();
					int celdaJ = theWay.get(k).getJ();
					//delay del programa
					delayASecond();
					ambient.getGrid()[celdaI][celdaJ].setBackground(JAmbient.TAXI_AGENT_COLOR);
				}
				
				//limpiar el diccionario y el arreglo auxiliar de caminos
				ambient.getCamino().cleanPadres();
				theWay.clear();
				
				if(careerOntoWay == 1)
				{
					ambient.getCamino().getAgent().setQuota(0);
					System.out.println("Cuota actual del agente: "+String.valueOf(ambient.getCamino().getAgent().getQuota()));
					JOptionPane.showMessageDialog(null, "Pasajeros de ambas carreras llevados", "Llevando al destino", 1);
				}
				
				//ademas comprobar el agente y el destino
				
				System.out.println(ambient.getCamino().getAgent());
				
				JOptionPane.showMessageDialog(null, "El agente se desplazó hacia el destino", "Llevando al destino", 1);
			}
			
		}
	}
	
	

}

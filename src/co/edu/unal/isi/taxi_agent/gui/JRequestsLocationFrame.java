package co.edu.unal.isi.taxi_agent.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import co.edu.unal.isi.taxi_agent.logic.Position;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOK) {
			JOptionPane.showMessageDialog(this, "That's all, let's work on it!");
			
			//Para ordenar los caminos
			ArrayList<Position> theWay = new ArrayList<>();

			for (int i=0;i<ambient.getCamino().getPeticiones().size();i++)
			{
				//Position agentpos = ambient.getCamino().getAgent().getPosition();
				int tmpi = new Integer(ambient.getCamino().getPeticiones().get(i).getStartPosition().getI());
				int tmpj = new Integer(ambient.getCamino().getPeticiones().get(i).getStartPosition().getJ());
				Position tmp = new Position(tmpi,tmpj);
				
				//buscar
				ambient.getCamino().searchingPetition(ambient.getSituation().getRequests().get(i));			
				
				
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
				
				System.out.println(theWay);
				
				/* esto funciona
				
				Iterator it = ambient.getCamino().getPadres().keySet().iterator();
				while(it.hasNext()){
				  int[] key = (int[]) it.next();
				  int value[] = ambient.getCamino().getPadres().get(key);
				  System.out.println("Clave: " + key[0]+" "+key[1] + " -> Valor: " + value[0]+" "+value[1]);
				} */
				
				
				//pintar camino
				for (int k = 0; k < theWay.size();k++)
				{
					int celdaI = theWay.get(k).getI();
					int celdaJ = theWay.get(k).getJ();
					ambient.getGrid()[celdaI][celdaJ].setBackground(JAmbient.TAXI_AGENT_COLOR);
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
				
				System.out.println(theWay);
				
				//pintar camino
				for (int k = 0; k < theWay.size();k++)
				{
					int celdaI = theWay.get(k).getI();
					int celdaJ = theWay.get(k).getJ();
					ambient.getGrid()[celdaI][celdaJ].setBackground(JAmbient.TAXI_AGENT_COLOR);
				}
				
				//limpiar el diccionario y el arreglo auxiliar de caminos
				ambient.getCamino().cleanPadres();
				theWay.clear();
				
				//ademas comprobar el agente y el destino
				
				System.out.println(ambient.getCamino().getAgent());
				
				JOptionPane.showMessageDialog(null, "El agente se desplazó hacia el destino", "Llevando al destino", 1);
				
			}
			
		}
	}
	
	

}

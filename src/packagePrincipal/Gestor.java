package packagePrincipal;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import Main.Egg;

public class Gestor extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable tablaProcesos1,tablaProcesos2;
	List<Procesos> listaP = new ArrayList<Procesos>();
	List<Segmento> listaS = new ArrayList<Segmento>();
	int orden = 1,espacioTotal=2048,cd,eleccion;
	public Segmento s1 = new Segmento(),s2 = new Segmento();
	int[] arregloIds;
	String def ="___",lineText,endLine,ultimaExpresion;
	Document doc;
	String[] instruccion;
	JComboBox cbOrden;
	Element lineElem,rootElem;
	private JTextField taConsola;
	Egg e = new Egg(); 
	
	public Gestor() {
		
		listaS.add(s1);
		listaS.add(s2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 450, 142);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		cbOrden = new JComboBox();
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"First Fit", "Worst Fit", "Best Fit"}));
		cbOrden.setBounds(10, 11, 147, 20);
		panelPrincipal.add(cbOrden);
		
		JScrollPane scSegmento1 = new JScrollPane();
		scSegmento1.setBounds(78, 60, 128, 34);
		panelPrincipal.add(scSegmento1);
		
		tablaProcesos1 = new JTable();
		scSegmento1.setViewportView(tablaProcesos1);
		
		JScrollPane scSegmento2 = new JScrollPane();
		scSegmento2.setBounds(227, 60, 128, 34);
		panelPrincipal.add(scSegmento2);
		
		tablaProcesos2 = new JTable();
		scSegmento2.setViewportView(tablaProcesos2);
		
		taConsola = new JTextField();
		taConsola.setBounds(10, 105, 430, 20);
		panelPrincipal.add(taConsola);
		taConsola.setColumns(10);
		taConsola.setBackground(Color.black);
		taConsola.setForeground(Color.white);
		taConsola.setCaretColor(Color.WHITE);
		crearSegmentos();
		taConsola.addKeyListener(this);
		repintarTablas();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== e.VK_ENTER) {
			eventosConsola();
			
		}else if(e.getKeyCode()== e.VK_UP) {
			taConsola.setText(ultimaExpresion);
			
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void crearSegmentos() {
		Procesos p = new Procesos(1,def,"libre",256);
		Procesos p1 = new Procesos(2,def,"libre",256);
		Procesos p2 = new Procesos(3,def,"libre",256);
		Procesos p3 = new Procesos(4,def,"libre",256);
		Procesos p4 = new Procesos(5,def,"libre",256);
		Procesos p5 = new Procesos(6,def,"libre",256);
		Procesos p6 = new Procesos(7,def,"libre",256);
		Procesos p7 = new Procesos(8,def,"libre",256);
		listaP.add(p);
		listaP.add(p1);
		listaP.add(p2);
		listaP.add(p3);
		listaP.add(p4);
		listaP.add(p5);
		listaP.add(p6);
		listaP.add(p7);
		s1.setId(1);
		s1.setP1(listaP.get(0));
		s1.setP2(listaP.get(1));
		s1.setP3(listaP.get(2));
		s1.setP4(listaP.get(3));
		s1.setEspacio(1024);
		s2.setEspacio(1024);
		s2.setId(2);
		s2.setP1(listaP.get(4));
		s2.setP1(listaP.get(5));
		s2.setP1(listaP.get(6));
		s2.setP1(listaP.get(7));
		listaS.add(s1);
		listaS.add(s2);
	}
	
	public void repintarTablas() {
		
		tablaProcesos1.setModel(new DefaultTableModel(
				new Object[][] {
				},new String[] {
					listaP.get(0).getNombre(),listaP.get(1).getNombre(),listaP.get(2).getNombre(),listaP.get(3).getNombre()
				}
			));
		tablaProcesos2.setModel(new DefaultTableModel(
				new Object[][] {
				},new String[] {
					listaP.get(4).getNombre(),listaP.get(5).getNombre(),listaP.get(6).getNombre(),listaP.get(7).getNombre()
				}
			));
	}
	
	public void agregarProceso(String nombre,int tamañoP) {
		if(espacioTotalSuficiente(tamañoP)) {
			if(!nombreExistente(nombre)) {	
				Iterator<Procesos> iterP = listaP.iterator();
				while(iterP.hasNext()) {
					Procesos p = iterP.next();
					for(int i=0;i<arregloIds.length;i++) {
						if(p.getId()==arregloIds[i]) {
							p.setName(nombre);
							p.setSpace(0);
							p.setStatus("libre");
							espacioTotal-=256;						
							if(p.getId()<=4) {
								s1.setEspacio(s1.getEspacio()-256);
								System.out.println("espacio total "+espacioTotal+" espacio s1 "+s1.getEspacio());
							}else {
								s2.setEspacio(s2.getEspacio()-256);
								System.out.println("espacio total "+espacioTotal+" espacio s2 "+s2.getEspacio());
							}
							
						}
					}
				}
				repintarTablas();
				taConsola.setText(taConsola.getText().substring(0,0));
			}else {
				taConsola.setText("Proceso ya existente");
				cd++;
				e.cd(cd, taConsola);
			}
		}
	}
	
	public boolean espacioTotalSuficiente(int necesario) {
		if(necesario <= espacioTotal) {
			return true;
		}		
		return false;
	}
	
	public void consecutivos(int tamaño) {
		int cont= 0,index=0;
		int[] ids = new int[tamaño/256];
		switch(eleccion) {
		case 0:	
			Iterator<Procesos> iterp = listaP.iterator();
			outerloop:
			while(iterp.hasNext()) {
				Procesos p = iterp.next();
				if(p.getSpace()==256 && p.getStatus().equals("libre")) {
					ids[index]=p.getId();
					cont+=256;
					index++;
					if(cont==tamaño) {
						arregloIds=ids;
						break outerloop;
					}
				}else {
					cont=0;
					index=0;
				}
			}
			break;
		case 2:
			Iterator<Segmento> iters = listaS.iterator();
			outerloop:
			while(iters.hasNext()) {
				Segmento s = iters.next();
				if(s.getEspacio() >= tamaño) {
					iterp = s.getLista().iterator();
					while(iterp.hasNext()) {
						Procesos p = iterp.next();
						if(p.getSpace()==256 && p .getStatus().equals("libre")) {
							System.out.println(p.getId());
							
							ids[index] = p.getId();
							cont+=256;
							index++;
							if(cont==tamaño) {
								arregloIds = ids;
								break outerloop;
							}
						}else {
							cont=0;
							index=0;
						}
					}
				}
			}
			break;
		case 1:
			if(s1.getEspacio()>s2.getEspacio() && s1.getEspacio()>=tamaño) {
				if(consecutivosDisponibles(tamaño,s1.getLista())) {
					
					Iterator<Procesos> iterp1 = s1.getLista().iterator();
					outerloop:
					while(iterp1.hasNext()) {
						Procesos p = iterp1.next();
						if(p.getSpace()==256 && p.getStatus().equals("libre")) {
							ids[index] = p.getId();
							cont+=256;
							index++;
							if(cont == tamaño) {
								arregloIds = ids;
								break outerloop;
							}
						}else{
							cont=0;
							index=0;
						}
					}
				}
			}else if(s2.getEspacio()>s1.getEspacio() && s2.getEspacio()>=tamaño){
				if(consecutivosDisponibles(tamaño,s2.getLista())) {
					
					Iterator<Procesos> iterp1 = s2.getLista().iterator();
					outerloop:
					while(iterp1.hasNext()) {
						Procesos p = iterp1.next();
						if(p.getSpace()==256 && p.getStatus().equals("libre")) {
							ids[index] = p.getId();
							cont+=256;
							index++;
							if(cont == tamaño) {
								arregloIds = ids;
								break outerloop;
							}
						}else{
							cont=0;
							index=0;
						}
					}
				}
			}else if(s1.getEspacio()==s2.getEspacio()) {
				if(consecutivosDisponibles(tamaño,s1.getLista())){
					Iterator<Procesos> iterp1 = s1.getLista().iterator();
					outerloop:
					while(iterp1.hasNext()) {
						Procesos p = iterp1.next();
						if(p.getSpace()==256 && p.getStatus().equals("libre")) {
							ids[index] = p.getId();
							cont+=256;
							index++;
							if(cont == tamaño) {
								arregloIds = ids;
								break outerloop;
							}
						}else{
							cont=0;
							index=0;
						}
					}
				}else if(consecutivosDisponibles(tamaño,s2.getLista())){
					Iterator<Procesos> iterp1 = s2.getLista().iterator();
					outerloop:
					while(iterp1.hasNext()) {
						Procesos p = iterp1.next();
						if(p.getSpace()==256 && p.getStatus().equals("libre")) {
							ids[index] = p.getId();
							cont+=256;
							index++;
							if(cont == tamaño) {
								arregloIds = ids;
								break outerloop;
							}
						}else{
							cont=0;
							index=0;
						}
					}
				}
				
			}
		break;			
		}
	}
	
	public boolean consecutivosDisponibles(int tamaño,List<Procesos> lista) {
		int cont = 0;
		Iterator<Procesos> iterp = lista.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getSpace()==256 && p.getStatus().equals("libre")) {
				cont+=256;
				if(cont == tamaño) {
					return true;
				}
			}else{
				cont =0;
			}
		}
		return false;
	}
	
	public int bitMultiplier(int memoria) {
		if(memoria>256) {
		int x = memoria / 256;
		
			if( x % 2 == 0) {
				System.out.println(x+" mayor");
				return x*256;
			}else {			
				x = (int)x;
				System.out.println(x+" igual redondeado");
				return x*256;
			}
		}else {
			return 256;
		}
	}
	
	public String[] getLastLine() {
		String[] text,instruction = null;
		taConsola.setCaretPosition(taConsola.getText().length());
		lineText = taConsola.getText();
		if(isAnExpression(lineText)) {
			ultimaExpresion=lineText;
			lineText = lineText.replace("'",""); 
			lineText = lineText.replace("\"", "");
			lineText = lineText.substring(0,lineText.length()-1);
			text = lineText.split("\\(");
			if(text[0].equals("add")) {
				String[] parameters = text[1].split(",");
				instruction = new String[] {text[0],parameters[0],parameters[1]};
			}else if(text[0].equals("kill")) {
				return text;
			}else if(text[0].equals("exit")) {
				
			}else {
				taConsola.setText("La funcion no es valida\n");
			}
		}else{
			taConsola.setText("La funcion no es valida\n");
		}
		return instruction;
	}	
	
	public boolean isAnExpression(String s) {
		Pattern patAdd = Pattern.compile("^[a-zA-Z]{3}[(](\"|\')[a-zA-Z]{1,3}(\"|\')[,][0-9]{3,4}[)]");
		Pattern patKill = Pattern.compile("^[a-zA-Z]{4}[(](\"|\')[a-zA-Z]{1,3}(\"|\')[)]");
		Pattern patExit = Pattern.compile("^[a-zA-Z]{4}[(][)]");
		Matcher matAdd = patAdd.matcher(s);
		Matcher matExit = patExit.matcher(s);
		Matcher matKill = patKill.matcher(s);
		if(matAdd.matches()||matKill.matches()|| matExit.matches()) {
			return true;
		}
		return false;
	}
	
	public void  eventosConsola() {
		int memoria;
		instruccion = getLastLine();
		if(instruccion != null) {
			if(instruccion[0].equals("add")){
				System.out.println(instruccion[2]);
				memoria = bitMultiplier(Integer.parseInt(instruccion[2]));
					System.out.println("memo: "+memoria);
					eleccion=cbOrden.getSelectedIndex();
					if(espacioTotalSuficiente(memoria)) {
						consecutivos(memoria);
						agregarProceso(instruccion[1],memoria);
					}else {
						taConsola.setText("Espacio total Insuficiente");
					}
							
			}else {
			
				killProceso(instruccion[1]);
				repintarTablas();
			}
		}
	}
	
	
	public void procesosALista() {

		listaP.clear();
		Iterator<Segmento> iters = listaS.iterator();
		while(iters.hasNext()) {
			Segmento s = iters.next();
			Iterator<Procesos> iterp = s.getLista().iterator();
			while(iterp.hasNext()) {
				Procesos p = iterp.next();
				listaP.add(p);
			}
		}
	}
	
	
	public void killProceso(String nombre) {
		Iterator<Procesos> iterp =listaP.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getNombre().equals(nombre)) {
				p.setName(def);
				p.setSpace(256);
				p.setStatus("libre");
				espacioTotal+=256;
				if(p.getId()<4) {
					s1.setEspacio(s1.getEspacio()+256);
				}else {
					s2.setEspacio(s2.getEspacio()+256);
				}
			}
		}
		repintarTablas();
	}
	
	
	public boolean nombreExistente(String nombre) {
		Iterator<Procesos> iterP = listaP.iterator();
		while(iterP.hasNext()) {
			Procesos p = iterP.next();
			if(p.getNombre().equals(nombre)){
				return true;
			}
		}
		return false;
	}
}

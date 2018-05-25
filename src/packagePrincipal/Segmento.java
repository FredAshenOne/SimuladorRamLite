package packagePrincipal;

import java.util.ArrayList;
import java.util.List;


public class Segmento {
	
	private int id,espacio;
	private Procesos p1,p2,p3,p4;
	private List<Procesos> listaProcesosSegmento = new ArrayList<Procesos>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public List<Procesos> getLista(){
		return listaProcesosSegmento;
	}

	public int getEspacio() {
		return espacio;
	}

	public void setEspacio(int espacio) {
		this.espacio = espacio;
	}

	public Procesos getP1() {
		return p1;
	}

	public void setP1(Procesos p1) {
		this.p1 = p1;
		listaProcesosSegmento.add(p1);
	}

	public Procesos getP2() {
		return p2;
	}

	public void setP2(Procesos p2) {
		this.p2 = p2;
		listaProcesosSegmento.add(p2);
	}

	public Procesos getP3() {
		return p3;
	}

	public void setP3(Procesos p3) {
		this.p3 = p3;
		listaProcesosSegmento.add(p3);
	}

	public Procesos getP4() {
		return p4;
	}

	public void setP4(Procesos p4) {
		this.p4 = p4;
		listaProcesosSegmento.add(p4);
	}
	
}

package packagePrincipal;

public class Procesos {

	private int id;
	private String nombre;
	private String status;
	private int espacio;
	
	public Procesos(int id,String nombre,String status,int espacio) {
		this.id=id;
		this.nombre=nombre;
		this.status=status;
		this.espacio=espacio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setName(String nombre) {
		this.nombre = nombre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSpace() {
		return espacio;
	}
	public void setSpace(int espacio) {
		this.espacio= espacio;
	}
	
}

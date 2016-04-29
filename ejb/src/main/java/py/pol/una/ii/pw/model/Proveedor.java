package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class Proveedor implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9018991189442878104L;
	/** Default value included to remove warning. Remove or modify at will. **/
    

  
    private Long id_proveedor;
    private String ruc;
    private String nombre;
    private String telefono;
	public Long getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(Long id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
    
}

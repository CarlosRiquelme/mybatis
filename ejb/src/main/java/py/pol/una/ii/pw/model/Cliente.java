package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5721193536209413888L;
	private Integer id_cliente;
    private String ruc;
    private String nombre;
    private String telefono;
    private Float saldo;
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
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
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}

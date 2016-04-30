package py.pol.una.ii.pw.model;

import java.io.Serializable;
import java.util.Date;

public class CompraCabecera implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3051916410557101964L;

	private Integer id_compraCabecera;

	private Proveedor proveedor;	
	private String fecha;
	private Float monto;
	public Integer getId_compraCabecera() {
		return id_compraCabecera;
	}
	public void setId_compraCabecera(Integer id_compraCabecera) {
		this.id_compraCabecera = id_compraCabecera;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}

	
	
}

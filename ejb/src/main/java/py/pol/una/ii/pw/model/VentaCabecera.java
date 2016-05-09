package py.pol.una.ii.pw.model;

import java.io.Serializable;
import java.util.Date;

public class VentaCabecera implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3051916410557101964L;

	private Integer id_ventaCabecera;

	private Integer id_cliente;	
	private Date fecha;
	private Float monto;
	public Integer getId_ventaCabecera() {
		return id_ventaCabecera;
	}

	public void setId_ventaCabecera(Integer id_ventaCabecera) {
		this.id_ventaCabecera = id_ventaCabecera;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getMonto() {
		return monto;
	}
	
	public void setMonto(Float monto) {
		this.monto = monto;
	}

	
	
}

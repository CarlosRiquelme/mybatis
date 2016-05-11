package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class VentaDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241814166023113538L;


	private Integer id_ventaDetalle;
	

	private Integer id_producto;

	private Float cantidad;
	private Float monto_parcial;


	private Integer id_ventacabecera;
	
	public Integer getId_ventaDetalle() {
		return id_ventaDetalle;
	}

	public void setId_ventaDetalle(Integer id_ventaDetalle) {
		this.id_ventaDetalle = id_ventaDetalle;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getMonto_parcial() {
		return monto_parcial;
	}

	public void setMonto_parcial(Float monto_parcial) {
		this.monto_parcial = monto_parcial;
	}

	public Integer getIdVentaCabecera() {
		return id_ventacabecera;
	}

	public void setIdVentaCabecera(Integer id_ventacabecera) {
		this.id_ventacabecera = id_ventacabecera;
	}

}

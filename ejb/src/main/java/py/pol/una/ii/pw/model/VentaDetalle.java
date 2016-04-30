package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class VentaDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241814166023113538L;


	private Integer id_ventaDetalle;
	

	private Producto producto;

	private Float cantidad;
	private Float monto_parcial;


	private VentaCabecera ventaCabecera;
	
	public Integer getId_ventaDetalle() {
		return id_ventaDetalle;
	}

	public void setId_ventaDetalle(Integer id_ventaDetalle) {
		this.id_ventaDetalle = id_ventaDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public VentaCabecera getVentaCabecera() {
		return ventaCabecera;
	}

	public void setVentaCabecera(VentaCabecera ventaCabecera) {
		this.ventaCabecera = ventaCabecera;
	}

}

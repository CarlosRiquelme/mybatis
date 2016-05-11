package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class CompraDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241814166023113538L;


	private Integer id_compraDetalle;
	

	private Integer id_producto;

	private Float cantidad;


	private Integer id_compracabecera;
	
	public Integer getId_compraDetalle() {
		return id_compraDetalle;
	}

	public void setId_compraDetalle(Integer id_compraDetalle) {
		this.id_compraDetalle = id_compraDetalle;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCompraCabecera() {
		return id_compracabecera;
	}

	public void setCompraCabecera(Integer id_compracabecera) {
		this.id_compracabecera = id_compracabecera;
	}

	public Integer getProducto() {
		return id_producto;
	}

	public void setProducto(Integer id_producto) {
		this.id_producto = id_producto;
	}

}

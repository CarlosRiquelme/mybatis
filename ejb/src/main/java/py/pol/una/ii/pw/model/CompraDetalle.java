package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class CompraDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241814166023113538L;


	private Integer id_compraDetalle;
	

	private Producto producto;

	private Float cantidad;


	private CompraCabecera compraCabecera;
	
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

	public CompraCabecera getCompraCabecera() {
		return compraCabecera;
	}

	public void setCompraCabecera(CompraCabecera compraCabecera) {
		this.compraCabecera = compraCabecera;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}

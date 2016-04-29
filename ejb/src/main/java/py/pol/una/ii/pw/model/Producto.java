package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929070974030694374L;
	private Integer id_producto;
	private String nombre;
	private Float precio_compra;
	private Float precio_venta;
	private Float cantidad;
	
	
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(Float precio_compra) {
		this.precio_compra = precio_compra;
	}
	public Float getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(Float precio_venta) {
		this.precio_venta = precio_venta;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}

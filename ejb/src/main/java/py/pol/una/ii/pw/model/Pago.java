package py.pol.una.ii.pw.model;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929070974030694374L;
	private Integer id_pago;
	private Date fecha;
	private Float monto; 
	private Cliente cliente;
	
	public Integer getId_Pago() {
		return id_pago;
	}
	public void setId_Pago(Integer id_pago) {
		this.id_pago = id_pago;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	


}

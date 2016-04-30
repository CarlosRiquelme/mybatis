package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.VentaDetalle;

public interface VentaDetalleMapper {
	

	public List<VentaDetalle> getAllVentaDetalle();
	
	public VentaDetalle selectVentaDetalleById(int id);
	
	public void insertVentaDetalle(VentaDetalle ventadetalle);

}

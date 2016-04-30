package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.CompraDetalle;

public interface CompraDetalleMapper {
	

	public List<CompraDetalle> getAllCompraDetalle();
	
	public CompraDetalle selectCompraDetalleById(int id);
	
	public void insertCompraDetalle(CompraDetalle compradetalle);

}

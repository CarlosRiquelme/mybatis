package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.VentaCabecera;

public interface VentaCabeceraMapper {
	
	public List<VentaCabecera> getAllVentaCabecera();
	
	public VentaCabecera selectVentaCabeceraById(int id);

	public void insertVentaCabecera(VentaCabecera ventaCabecera);

}

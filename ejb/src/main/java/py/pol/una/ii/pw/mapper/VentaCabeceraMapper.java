package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.CompraCabecera;

public interface VentaCabeceraMapper {
	
	public List<VentaCabecera> getAllVentaCabecera();
	
	public VentaCabecera selectVentaCabeceraById(int id);
	
	

}

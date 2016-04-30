package py.pol.una.ii.pw.mapper;

import java.util.List;
 
import py.pol.una.ii.pw.model.Pago;

public interface PagoMapper {
	
	public List<Pago> getAllPago();
	
	public Pago selectPagoById(int id);

	public void insertPago(Pago pago);
	
	public void updatePago(Pago pago);
	
	public void deletePago(int id);

}

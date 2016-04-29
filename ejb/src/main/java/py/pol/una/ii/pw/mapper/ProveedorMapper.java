package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.Proveedor;

public interface ProveedorMapper {
	
	public Proveedor selectProveedorById(int id);

	public List<Proveedor> getAllProveedor();
	
	public void insertProveedor(Proveedor proveedor);
	
	public void updateProveedor(Proveedor proveedor);
	
	public void deleteProveedor(int id);

}

package py.pol.una.ii.pw.mapper;

import java.util.List;

import py.pol.una.ii.pw.model.Cliente;

public interface ClienteMapper {
	
	public Cliente selectClienteById(int id);

	public List<Cliente> getAllCliente();
	
	public void insertCliente(Cliente cliente);
	
	public void updateCliente(Cliente cliente);
	
	public void deleteCliente(int id);

}

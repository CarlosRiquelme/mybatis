package py.pol.una.ii.pw.mapper;


import py.pol.una.ii.pw.model.Usuario;
import py.pol.una.ii.pw.util.Credentials;

public interface AuthenticateMapper {
	
	public String insertUsuario(Usuario usuario);
	
	public void updateUsuario(Usuario usuario);
	
	public Usuario selectUsuario (Credentials credentials);
	
	public Usuario selectToken(String token);

}

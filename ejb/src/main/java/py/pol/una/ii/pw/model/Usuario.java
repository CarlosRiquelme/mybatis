package py.pol.una.ii.pw.model;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = -5721193536209413888L;
	private Integer id_usuario;
    private String usuario;
    private String pass;
    private String rol;
    private String acces_token;
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getAcces_token() {
		return acces_token;
	}
	public void setAcces_token(String acces_token) {
		this.acces_token = acces_token;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    


    
    
    
    
	
}

package py.pol.una.ii.pw.util;

import java.io.Serializable;

public class Credentials implements Serializable {

    private String username;
    private String password;
    private String rol;
    
    
    
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    

   
}
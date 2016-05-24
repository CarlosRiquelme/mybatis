package py.pol.una.ii.pw.rest;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.ii.pw.model.Usuario;
import py.pol.una.ii.pw.service.AuthenticateService;
import py.pol.una.ii.pw.service.Token;
import py.pol.una.ii.pw.util.Credentials;

@Path("/autenticar")
@RequestScoped
public class AuthenticationRESTService {
	
	 	@POST
	 	@Produces(MediaType.APPLICATION_JSON)
	 	@Consumes(MediaType.APPLICATION_JSON)
	    public Response autenticar(Usuario usuario) {
	 		
	 		Response.ResponseBuilder builder = null;
	 		
	        try {


	        	Credentials c=new Credentials();
	        	c.setUsername(usuario.getUsuario());
	            c.setPassword(usuario.getPass());
	            Usuario user=AuthenticateService.selectUsuario(c);
	            String token=user.getAcces_token();
	            System.out.println("token final -->"+token);

	            // Return the token on the response
	            return Response.ok(token).build();

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            // Handle generic exceptions
	            Map<String, String> responseObj = new HashMap<String, String>();
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	        }      
	        return builder.build();
	        		
	    }


}

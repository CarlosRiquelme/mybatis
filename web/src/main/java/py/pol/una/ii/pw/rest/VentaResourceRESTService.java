package py.pol.una.ii.pw.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.ii.pw.model.Usuario;
import py.pol.una.ii.pw.model.VentaCabecera;
import py.pol.una.ii.pw.service.AuthenticateService;
import py.pol.una.ii.pw.service.CompraCabeceraService;
import py.pol.una.ii.pw.service.VentaCabeceraService;
import py.pol.una.ii.pw.util.Venta;

@Path("/ventas")
@RequestScoped
public class VentaResourceRESTService {
	
	 	@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<VentaCabecera> listAllVentas(@HeaderParam("Authorization") String aut) {
	 		Usuario user=AuthenticateService.verificarToken(aut,"VENT") ;
	 		if( user != null){
	    	List<VentaCabecera> ventas = VentaCabeceraService.getAllVentaCabecera();
	    	System.out.println("Objeto a retornar" + ventas.size());
	        return ventas;
	 		}else{
	 			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	 		}
	    }

	    @GET
	    @Path("/{id:[0-9][0-9]*}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public VentaCabecera lookupCompraById(@PathParam("id") Integer id) {
	    	VentaCabecera venta = VentaCabeceraService.selectVentaCabeceraById(id);
	        if (venta == null) {
	            throw new WebApplicationException(Response.Status.NOT_FOUND);
	        }
	        System.out.println("Objeto a retornar" + venta);
	        return venta;
	    }
	    
	    @POST
	    @Path("/nueva")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response createVenta(Venta venta,@HeaderParam("Authorization") String aut ) {
	    	
	 		Usuario user=AuthenticateService.verificarToken(aut,"VENT") ;
	 		if( user != null){
		        Response.ResponseBuilder builder = null;
		        try {
		            VentaCabeceraService.registerVenta(venta);
		            // Create an "ok" response
		            builder = Response.ok();
		        }catch (Exception e) {
		        	System.out.println(e.getMessage());
		            // Handle generic exceptions
		            Map<String, String> responseObj = new HashMap<String, String>();
		            responseObj.put("error", e.getMessage());
		            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		        }
		        return builder.build();
	 		}else{
	 			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	 		}
	    
	    
	    	

	    }
	    
	    
	
	

}

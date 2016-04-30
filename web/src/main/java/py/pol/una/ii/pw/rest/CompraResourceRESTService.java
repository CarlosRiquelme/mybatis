package py.pol.una.ii.pw.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.ii.pw.model.CompraCabecera;
import py.pol.una.ii.pw.service.CompraCabeceraService;
import py.pol.una.ii.pw.util.Compra;

@Path("/compras")
@RequestScoped
public class CompraResourceRESTService {
	
	 	@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<CompraCabecera> listAllCompras() {
	    	List<CompraCabecera> compras = CompraCabeceraService.getAllCompraCabecera();
	    	System.out.println("Objeto a retornar" + compras.size());
	        return compras;
	    }

	    @GET
	    @Path("/{id:[0-9][0-9]*}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public CompraCabecera lookupCompraById(@PathParam("id") Integer id) {
	    	CompraCabecera compra = CompraCabeceraService.selectCompraCabeceraById(id);
	        if (compra == null) {
	            throw new WebApplicationException(Response.Status.NOT_FOUND);
	        }
	        System.out.println("Objeto a retornar" + compra);
	        return compra;
	    }
	    
	    @POST
	    @Path("/nueva")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response createCompra(Compra compra) {
	    	System.out.println("Objeto recibido---" + compra);
	        Response.ResponseBuilder builder = null;
	        try {
	            CompraCabeceraService.registerCompra(compra);

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
	    }
	    
	    
	
	

}

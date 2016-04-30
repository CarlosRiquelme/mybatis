package py.pol.una.ii.pw.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.ii.pw.model.CompraCabecera;
import py.pol.una.ii.pw.service.CompraCabeceraService;

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
	    
	    
	
	

}

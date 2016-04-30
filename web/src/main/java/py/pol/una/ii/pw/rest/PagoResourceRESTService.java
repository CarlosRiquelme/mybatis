package py.pol.una.ii.pw.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.ii.pw.model.Pago;
import py.pol.una.ii.pw.service.PagoService;

@Path("/pagos")
@RequestScoped
public class PagoResourceRESTService {
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> listAllClientes() {
    	List<Pago> pago = PagoService.getAllPago();
    	System.out.println("Objeto a retornar" + pago);
        return pago;
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pago lookupPagoById(@PathParam("id") Integer id) {
    	Pago pago = PagoService.selectPagoById(id);
        if (pago == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        System.out.println("Objeto a retornar" + pago);
        return pago;
    }

    /**
     * Creates a new pago from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Path("/nuevo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPago(Pago pago) {
    	System.out.println("Objeto recibido---" + pago);
        Response.ResponseBuilder builder = null;
        try {
            PagoService.createPago(pago);
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
    
    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterPago(Pago pago) {
    	System.out.println("Objeto recibido---" + pago);
        Response.ResponseBuilder builder = null;
        try {
        	PagoService.updatePago(pago);
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
    
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePago(@PathParam("id") Integer id) {
    	System.out.println("Objeto recibido---" + id);
        Response.ResponseBuilder builder = null;
        try { 
        	PagoService.deletePago(id);
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

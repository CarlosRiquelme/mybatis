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

import py.pol.una.ii.pw.model.Proveedor;
import py.pol.una.ii.pw.service.ProveedorService;


@Path("/proveedores")
@RequestScoped
public class ProveedorResourceRESTService {
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proveedor> listAllProveedor() {
    	List<Proveedor> proveedor = ProveedorService.getAllProveedor();
    	System.out.println("Objeto a retornar" + proveedor);
        return proveedor;
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proveedor lookupProveedorById(@PathParam("id") Integer id) {
    	Proveedor proveedor = ProveedorService.selectProveedorById(id);
        if (proveedor == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        System.out.println("Objeto a retornar" + proveedor);
        return proveedor;
    }

    /**
     * Creates a new proveedor from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Path("/nuevo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProveedor(Proveedor proveedor) {
    	System.out.println("Objeto recibido---" + proveedor);
        Response.ResponseBuilder builder = null;
        try {
        	
        	
            ProveedorService.createProveedor(proveedor);
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
    public Response alterProveedor(Proveedor proveedor) {
    	System.out.println("Objeto recibido---" + proveedor);
        Response.ResponseBuilder builder = null;
        try {
            ProveedorService.updateProveedor(proveedor);
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
    public Response removeProveedor(@PathParam("id") Integer id) {
    	System.out.println("Objeto recibido---" + id);
        Response.ResponseBuilder builder = null;
        try {
        	Proveedor proveedor =ProveedorService.selectProveedorById(id);
            ProveedorService.deleteProveedor(id);
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

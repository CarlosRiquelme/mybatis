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

import py.pol.una.ii.pw.model.Cliente;
import py.pol.una.ii.pw.service.ClienteService;

@Path("/clientes")
@RequestScoped
public class ClienteResourceRESTService {
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listAllClientes() {
    	List<Cliente> clientes = ClienteService.getAllCliente();
    	System.out.println("Objeto a retornar" + clientes);
        return clientes;
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente lookupClienteById(@PathParam("id") Integer id) {
    	Cliente cliente = ClienteService.selectClienteById(id);
        if (cliente == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        System.out.println("Objeto a retornar" + cliente);
        return cliente;
    }

    /**
     * Creates a new cliente from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Path("/nuevo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(Cliente cliente) {

        Response.ResponseBuilder builder = null;
        try {
        	Float inicial = (float) 0;
        	cliente.setSaldo(inicial);
            ClienteService.createCliente(cliente);
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
    public Response alterCliente(Cliente cliente) {
    	System.out.println("Objeto recibido---" + cliente);
        Response.ResponseBuilder builder = null;
        try {
            ClienteService.updateCliente(cliente);
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
    public Response removeCliente(@PathParam("id") Integer id) {
    	System.out.println("Objeto recibido---" + id);
        Response.ResponseBuilder builder = null;
        try {
        	Cliente cliente =ClienteService.selectClienteById(id);
            ClienteService.deleteCliente(id);
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

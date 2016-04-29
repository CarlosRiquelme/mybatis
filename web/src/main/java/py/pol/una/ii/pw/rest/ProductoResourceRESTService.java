package py.pol.una.ii.pw.rest;

import java.io.IOException;
import java.util.Collections;
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
import javax.ws.rs.core.StreamingOutput;

import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.service.ProductoService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

@Path("/productos")
@RequestScoped
public class ProductoResourceRESTService {
	
	
	private String getRepresentation(ObjectMapper om, Producto p) {
		try {
			return om.writeValueAsString(p);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProductos() {
		try {
			StreamingOutput stream = new StreamingOutput() {

				@Override
				public void write(OutputStream arg0) throws IOException, WebApplicationException {
					Writer w = new BufferedWriter(new OutputStreamWriter(arg0));

					ObjectMapper om = new ObjectMapper();
					
					Integer cantidad = 3; // 100
					
					w.write("[");
					Integer leidos = cantidad;
					Integer desde = 0;
					boolean first = true;
					while (leidos == cantidad) {

						// En las siguientes iteraciones, esta lista no esta mas
						// referenciada, entonces es viable que el garbage collector la 
						// limpie
						List<Producto> actuales = ProductoService.getAllProducto(desde, cantidad);
						desde += actuales.size();
						leidos = actuales.size();
						for (Producto p : actuales) {
							if (first) {
								first = false;
								w.write(getRepresentation(om, p));
							} else {
								w.write("," + getRepresentation(om, p));
							}
						}

						w.flush();
					}
					w.write("]");
					w.flush();
				}
			};

			return Response.ok(stream).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto lookupProductoById(@PathParam("id") Integer id) {
    	Producto producto = ProductoService.selectProductoById(id);
        if (producto == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        System.out.println("Objeto a retornar" + producto);
        return producto;
    }

    /**
     * Creates a new producto from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
	@POST
	@Path("/nuevo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProducto(Producto producto) {
		System.out.println("Objeto recibido---" + producto);
		Response.ResponseBuilder builder = null;
		try {
			ProductoService.createProducto(producto);

			// Create an "ok" response
			builder = Response.ok();
		} catch (Exception e) {
			if (e.getCause().getCause().getMessage().contains("nombre")) {
				builder = Response.status(Response.Status.BAD_REQUEST)
						.entity(Collections.singletonMap("error", "Nombre duplicado"));
				ProductoService.guardarDuplicado(producto);
			} else {
				System.out.println(e.getMessage());
				// Handle generic exceptions
				Map<String, String> responseObj = new HashMap<String, String>();
				responseObj.put("error", e.getMessage());
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}

		return builder.build();
	}
    
    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterProducto(Producto producto) {
    	System.out.println("Objeto recibido---" + producto);
        Response.ResponseBuilder builder = null;
        try {
            ProductoService.updateProducto(producto);
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
	@POST
	@Path("/muchos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProducto(List<Producto> producto) {
		Integer i = 0;
		for (Producto p : producto) {
			try {
				ProductoService.createProducto(p);
				i++;
			} catch (Exception e) {
				if (e.getCause().getCause().getMessage().contains("nombre")) {
					ProductoService.guardarDuplicado(p);
				} else {
					System.out.println("Por algun motivo no se pudo guardar");
				}
			}
		}
		return Response.ok().entity(Collections.singletonMap("mensaje", "Se metieron un total de " + i + " productos"))
				.build();
	}
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProducto(@PathParam("id") Integer id) {
    	System.out.println("Objeto recibido---" + id);
        Response.ResponseBuilder builder = null;
        try {
        	Producto producto =ProductoService.selectProductoById(id);
            ProductoService.deleteProducto(id);
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

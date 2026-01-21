package es.upsa.dasi.templates.resources;

import es.upsa.dasi.templates.domain.Producto;
import es.upsa.dasi.templates.domain.Repository;
import es.upsa.dasi.templates.infrastructure.RepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/productos")
public class ProductosResource {

    @Inject
    Repository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProductos() {
        return Response.ok()
                .entity(repository.findProductos())
                .build();
    }

    @Path("/{idProducto}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, "text/csv"})
    public Response findProductoById(@PathParam("idProducto") String idProducto) {
        Optional<Producto> producto = repository.findProductoById(idProducto);
        if (producto.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(producto.get()).build();
    }

}

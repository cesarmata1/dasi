package es.upsa.dasi.templates.infrastructure;

import es.upsa.dasi.templates.domain.Producto;
import es.upsa.dasi.templates.domain.Repository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    private Map<String, Producto> productos;


    @PostConstruct
    public void init() {
        productos = new HashMap<>();
        productos.put("1", new Producto("1", "Martillo", 20.00));
        productos.put("2", new Producto("2", "Tornillo", 1.50));
        productos.put("3", new Producto("3", "Taladro", 90.20));
        productos.put("4", new Producto("4", "Yeso", 16.00));
        productos.put("5", new Producto("5", "Tenaza", 15.00));

    }

    @Override
    public Collection<Producto> findProductos() {
        return productos.values();
    }

    @Override
    public Optional<Producto> findProductoById(String id) {
        return Optional.ofNullable(productos.get(id));
    }
}

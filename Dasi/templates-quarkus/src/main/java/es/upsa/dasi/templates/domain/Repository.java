package es.upsa.dasi.templates.domain;

import java.util.Collection;
import java.util.Optional;

public interface Repository {
    public Collection<Producto> findProductos();
    public Optional<Producto> findProductoById(String id);
}

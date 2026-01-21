package es.upsa.dasi.templates.resources.providers;

import es.upsa.dasi.templates.infrastructure.csv.ProductoCSVAdapter;
import jakarta.ws.rs.ext.ContextResolver;

public class CSVAdapterContextResolver implements ContextResolver<CSVAdapter> {
    @Override
    public CSVAdapter getContext(Class<?> aClass) {
        if (CSVAdapter.class.isAssignableFrom(aClass)) {
            return new ProductoCSVAdapter();
        }
        return null;
    }
}

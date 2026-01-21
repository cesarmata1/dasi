package es.upsa.dasi.templates.infrastructure.csv;

import es.upsa.dasi.templates.domain.Producto;
import es.upsa.dasi.templates.resources.providers.CSVAdapter;

import java.io.IOException;
import java.io.Writer;

public class ProductoCSVAdapter implements CSVAdapter<Producto> {
    @Override
    public void objetctToCSV(Producto data, Writer writer) {
        try {
            String csvLine = String.format("%s;%s;%g", data.getId(), data.getNombre(), data.getPrecio());
            writer.write(csvLine);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

    }
}

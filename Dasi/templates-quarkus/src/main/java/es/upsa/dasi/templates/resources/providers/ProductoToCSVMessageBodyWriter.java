package es.upsa.dasi.templates.resources.providers;

import es.upsa.dasi.templates.domain.Producto;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

import static es.upsa.dasi.templates.infrastructure.csv.ClassUtils.getObjectClass;


@Provider
@Produces("text/csv")
public class ProductoToCSVMessageBodyWriter implements MessageBodyWriter {

    @Context
    Providers providers;

    @Override
    public boolean isWriteable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Producto.class.isAssignableFrom(aClass);
    }

    @Override
    public void writeTo(Object dato, Class aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        ContextResolver<CSVAdapter> contextResolver = providers.getContextResolver(CSVAdapter.class, MediaType.valueOf("text/csv"));
        if (contextResolver != null) {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);


            csvAdapter.toCSV(dato, aClass, type, printWriter);

            printWriter.flush();

        }
    }


    public Optional <CSVAdapter> findCsvAdapter (Class aClass, Type type)
    {
        ContextResolver<CSVAdapter> contextResolver = providers.getContextResolver(CSVAdapter.class, MediaType.valueOf("text/csv"));
        if (contextResolver != null) {
            Class objectClass = getObjectClass(aClass, type);
            return Optional.ofNullable(contextResolver.getContext(objectClass));
        }
        return Optional.empty()
    }

}

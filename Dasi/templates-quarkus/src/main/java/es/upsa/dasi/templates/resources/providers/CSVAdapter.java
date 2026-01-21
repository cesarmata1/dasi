package es.upsa.dasi.templates.resources.providers;

import es.upsa.dasi.templates.infrastructure.csv.ClassUtils;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collection;

public interface CSVAdapter<T> {

    default void toCSV(Object object, Class aClass, Type type, Writer writer){
        if (ClassUtils.isArray(aClass)) {
            arrayToCSV((T[]) object, writer);
        } else if (ClassUtils.isCollection(type)) {
            collectionToCSV((Collection<T>) object, writer);
        } else {
            objetctToCSV((T) object, writer);
        }
    }

    void objetctToCSV(T data, Writer writer);

    default void arrayToCSV(T[] array, Writer writer){
        for (T item : array) {
            objetctToCSV(item, writer);
        }
    }

    default void collectionToCSV(Collection<T> collection, Writer writer){
        for (T item : collection) {
            objetctToCSV(item, writer);
        }
    }


}

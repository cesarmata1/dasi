package es.upsa.dasi.templates.infrastructure.csv;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class ClassUtils {
    public static Class getObjectClass(Class aClass, Type type) {
        if (isArray(aClass)) {
            return aClass.getComponentType();
        }

        if (type instanceof ParameterizedType parameterizedType) {
            Class rawClass = (Class) parameterizedType.getRawType();
            if (Collection.class.isAssignableFrom(rawClass)) {
                Class componentClass = (Class) parameterizedType.getActualTypeArguments()[0];
                return componentClass;
            }
        }
        return aClass;
    }

    public static boolean isArray(Class aClass){
        return aClass.isArray();
    }

    public static boolean isCollection(Type type){
        if (type instanceof ParameterizedType parameterizedType) {
            Class rawClass = (Class) parameterizedType.getRawType();
            return Collection.class.isAssignableFrom(rawClass);
        }
        return false;
    }
}

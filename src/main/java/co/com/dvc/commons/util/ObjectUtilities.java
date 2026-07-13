package co.com.dvc.commons.util;

import co.com.dvc.commons.exception.InfrastructureException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectUtilities {

    public static Object getAttribute(Object object, String name) {
        try {
            return BeanUtils.getProperty(object, name);
        } catch (Exception e) {
            throw new InfrastructureException("Problemas al obtener el atributo: " + name);
        }
    }

    public static List<Object> getListAttribute(Object object, String name) {
        try {
            Object value = PropertyUtils.getProperty(object, name);

            if (value != null && value.getClass().isArray()) {
                List<Object> list = new ArrayList<>();
                int length = Array.getLength(value);

                for (int i = 0; i < length; i++) {
                    Object element = Array.get(value, i);
                    list.add(element);
                }

                return list;
            } else if (value instanceof List) {
                return new ArrayList<>((List<?>) value);
            } else {
                throw new InfrastructureException("La propiedad " + name + " no es una lista");
            }
        } catch (Exception e) {
            throw new InfrastructureException("Problemas al obtener la lista: " + name, e);
        }
    }

    public static String getStringAttribute(Object object, String name) {
        try {
            return BeanUtils.getProperty(object, name);
        } catch (Exception e) {
            return "";
        }
    }

    public static Integer getIntegerAttribute(Object object, String name) {
        try {
            String value = BeanUtils.getProperty(object, name);
            if (value != null) {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static Long getLongAttribute(Object object, String name) {
        try {
            String value = BeanUtils.getProperty(object, name);
            if (value != null) {
                return Long.parseLong(value);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static Boolean getBooleanAttribute(Object object, String name) {
        try {
            String value = BeanUtils.getProperty(object, name);
            if (value != null) {
                return Boolean.parseBoolean(value);
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.FALSE;
    }
}

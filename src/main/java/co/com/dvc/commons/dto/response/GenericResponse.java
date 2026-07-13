package co.com.dvc.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> implements Serializable {

    private boolean ok;
    private Integer codigo;
    private String mensaje;
    private T dato;
    private List<T> datos;
    private Integer conteo;
    private String totales;

    public static <T> GenericResponse<T> success(Integer codigo, String mensaje, T dato) {
        return GenericResponse.<T>builder()
                .ok(true)
                .codigo(codigo)
                .mensaje(mensaje)
                .dato(dato)
                .build();
    }

    public static <T> GenericResponse<T> success(Integer codigo, String mensaje, List<T> datos) {
        return GenericResponse.<T>builder()
                .ok(true)
                .codigo(codigo)
                .mensaje(mensaje)
                .datos(datos)
                .conteo(datos != null ? datos.size() : 0)
                .build();
    }

    public static <T> GenericResponse<T> successPaginated(
            Integer codigo,
            String mensaje,
            List<T> datos,
            Integer conteo,
            String totales) {
        return GenericResponse.<T>builder()
                .ok(true)
                .codigo(codigo)
                .mensaje(mensaje)
                .datos(datos)
                .conteo(conteo)
                .totales(totales)
                .build();
    }

    public static <T> GenericResponse<T> error(Integer codigo, String mensaje) {
        return GenericResponse.<T>builder()
                .ok(false)
                .codigo(codigo)
                .mensaje(mensaje)
                .build();
    }
}

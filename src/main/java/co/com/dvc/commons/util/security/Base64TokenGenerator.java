package co.com.dvc.commons.util.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class Base64TokenGenerator {
    private String entidad;
    private String codigoServicio;
    private int hashCodeResult;

    public Base64TokenGenerator(String entidad, String codigoServicio) {
        this.entidad = entidad;
        this.codigoServicio = codigoServicio;
    }

    @Override
    public int hashCode() {
        int prime = (getEntidad() != null && getEntidad().matches("\\d+"))
                ? Integer.parseInt(getEntidad())
                : 31;
        hashCodeResult = 1;
        setCodigoServicio("11");
        hashCodeResult = prime * hashCodeResult
                + ((getCodigoServicio() == null) ? 0
                : getCodigoServicio().hashCode());
        hashCodeResult = prime * hashCodeResult
                + ((getEntidad() == null) ? 0 : getEntidad().hashCode());
        return hashCodeResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Base64TokenGenerator other = (Base64TokenGenerator) obj;
        if (getCodigoServicio() == null) {
            if (other.getCodigoServicio() != null)
                return false;
        } else if (!getCodigoServicio().equals(other.getCodigoServicio()))
            return false;
        if (getEntidad() == null) {
            return other.getEntidad() == null;
        } else return getEntidad().equals(other.getEntidad());
    }

    public String base64Hash() {
        Cripto cri = new Cripto();
        String b64 = null;
        try {
            b64 = cri.encodeBase64(String.valueOf(hashCode()));
        } catch (Exception e) {
            log.error("Error al generar el hash Base64", e);
        }
        return b64;
    }

    public boolean validarToken(String tokenValidar) {
        try {
            if (base64Hash().equals(tokenValidar))
                return true;
        } catch (Exception e) {
            log.error("Error al validar el token", e);
        }
        return false;
    }

    public static String generateToken(String payload) {
        Base64TokenGenerator generator = new Base64TokenGenerator(payload, "default");
        return generator.base64Hash();
    }

    public static String decodeToken(String token) {
        return token;
    }
}

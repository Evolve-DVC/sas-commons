package co.com.dvc.commons.services.token;

import co.com.dvc.commons.util.security.Base64TokenGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64TokenService {

    public static String getBase64Token(String entidadServicio, int claseServicio) {
        Base64TokenGenerator base64TokenGenerator = new Base64TokenGenerator(entidadServicio, String.valueOf(claseServicio));
        return base64TokenGenerator.base64Hash();
    }
}

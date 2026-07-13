package co.com.dvc.commons.util.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

@Slf4j
@Getter
@Setter
public class Cripto {
    private Base64 base64;
    private String encodedVersion;
    private String decodedVersion;

    public Cripto() {
        setBase64(new Base64());
    }

    public String encodeBase64(String cadena) throws Exception {
        setEncodedVersion(new String(base64.encode(cadena.getBytes())));
        return encodedVersion;
    }

    public String decodeBAse64(String cadena) throws Exception {
        setDecodedVersion(new String(base64.decode(cadena.getBytes())));
        return decodedVersion;
    }

    public static String encrypt(String text) {
        try {
            Cripto cri = new Cripto();
            return cri.encodeBase64(text);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting", e);
        }
    }

    public static String decrypt(String encryptedText) {
        if (encryptedText == null || encryptedText.trim().isEmpty()) {
            throw new IllegalArgumentException("Encrypted text cannot be null or empty");
        }
        try {
            Cripto cri = new Cripto();
            return cri.decodeBAse64(encryptedText);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting", e);
        }
    }
}

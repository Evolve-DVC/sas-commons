package co.com.dvc.commons.services.i18;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String key) {
        return getMessage(key, (Object[]) null);
    }

    public String getMessage(String key, Object... params) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, params, locale);
        } catch (Exception e) {
            log.warn("No se encontró traducción para la clave '{}'. Retornando la clave como mensaje.", key);
            return key;
        }
    }

    public String getMessage(String key, Locale locale) {
        return getMessage(key, locale, (Object[]) null);
    }

    public String getMessage(String key, Locale locale, Object... params) {
        try {
            return messageSource.getMessage(key, params, locale);
        } catch (Exception e) {
            log.warn("No se encontró traducción para la clave '{}' en locale '{}'. Retornando la clave como mensaje.", key, locale);
            return key;
        }
    }
}

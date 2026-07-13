package co.com.dvc.commons.helper;

import co.com.dvc.commons.constants.MessageKeys;
import co.com.dvc.commons.dto.response.GenericResponse;
import co.com.dvc.commons.services.i18.MessageService;
import co.com.dvc.commons.services.pageable.IPageableResult;
import co.com.dvc.commons.services.pageable.PageableResultImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiResponseBuilder {
    private final MessageService messageService;

    private String getMessage(String key, Object... params) {
        try {
            return messageService.getMessage(key, params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return key;
        }
    }

    public <T> GenericResponse<T> success(T obj, String mensaje) {
        return GenericResponse.success(HttpStatus.OK.value(), mensaje, obj);
    }

    public <T> GenericResponse<T> successList(List<T> list, String mensaje) {
        return GenericResponse.success(HttpStatus.OK.value(), mensaje, list != null ? list : Collections.emptyList());
    }

    public <T> GenericResponse<T> paginated(IPageableResult<T> pageableResult, String mensaje) {
        if (pageableResult == null || pageableResult.getTotalElements() == 0) {
            return GenericResponse.success(HttpStatus.OK.value(), getMessage(MessageKeys.SUCCESS_NO_RESULTS), Collections.emptyList());
        }

        int totalPages = (int) Math.ceil((double) pageableResult.getTotalElements() / pageableResult.getPageSize());
        int currentPage = pageableResult.getPageNumber() + 1;

        return GenericResponse.successPaginated(
                HttpStatus.OK.value(),
                mensaje,
                pageableResult.getContent(),
                pageableResult.getTotalElements().intValue(),
                getMessage(MessageKeys.SUCCESS_PAGE_INFO, currentPage, totalPages)
        );
    }

    public <T> GenericResponse<T> paginated(Page<T> page, String mensaje) {
        if (page == null || page.getTotalElements() == 0) {
            return GenericResponse.success(HttpStatus.OK.value(), getMessage(MessageKeys.SUCCESS_NO_RESULTS), Collections.emptyList());
        }

        return paginated(new PageableResultImpl<>(page), mensaje);
    }

    public <T> GenericResponse<T> noContent(String mensaje) {
        return GenericResponse.success(HttpStatus.NO_CONTENT.value(), mensaje, (T) null);
    }

    public <T> GenericResponse<T> error(Throwable ex) {
        return GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    public <T> GenericResponse<T> error(Throwable ex, Integer errorCode) {
        return GenericResponse.error(errorCode, ex.getMessage());
    }

    public <T> GenericResponse<T> error(String mensaje) {
        return GenericResponse.error(HttpStatus.BAD_REQUEST.value(), mensaje);
    }

    public <T> GenericResponse<T> error(Integer errorCode, String mensaje) {
        return GenericResponse.error(errorCode, mensaje);
    }

    public <T> GenericResponse<T> badRequest(String mensaje) {
        return GenericResponse.error(HttpStatus.BAD_REQUEST.value(), mensaje);
    }

    public <T> GenericResponse<T> notFound(String mensaje) {
        return GenericResponse.error(HttpStatus.NOT_FOUND.value(), mensaje);
    }

    public <T> GenericResponse<T> unauthorized(String mensaje) {
        return GenericResponse.error(HttpStatus.UNAUTHORIZED.value(), mensaje);
    }

    public <T> GenericResponse<T> forbidden(String mensaje) {
        return GenericResponse.error(HttpStatus.FORBIDDEN.value(), mensaje);
    }
}

package co.com.dvc.commons.cqrs;

import co.com.dvc.commons.exception.DomainException;
import co.com.dvc.commons.services.IGenericService;
import co.com.dvc.commons.constants.MessageKeys;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class ComboQueryAbstract<T, K> extends QueryAbstract<T, List<T>> {
    private final IGenericService<T, K> service;

    protected ComboQueryAbstract(IGenericService<T, K> service) {
        this.service = service;
    }

    @Override
    protected T preProcess(T context) throws DomainException {
        if (context == null) {
            log.error(MessageKeys.ERROR_DOMAIN_VALID_CONTEXTO_NULL);
            throw new DomainException(MessageKeys.ERROR_DOMAIN_VALID_CONTEXTO_NULL);
        }
        return context;
    }

    @Override
    protected List<T> process(T context) {
        return this.service.getComboSencillo(context);
    }
}

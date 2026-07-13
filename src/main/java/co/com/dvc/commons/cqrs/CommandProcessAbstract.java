package co.com.dvc.commons.cqrs;

import co.com.dvc.commons.exception.DomainException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

@Slf4j
public abstract class CommandProcessAbstract<C, R> {

    public R execute(C context) throws DomainException {
        try {
            C validatedContext = preProcess(context);

            if (validatedContext == null) {
                log.warn("El proceso de comando {} fue invalidado en el pre-procesamiento", this.getClass().getSimpleName());
                return null;
            }

            R result = process(validatedContext);

            return postProcess(result);

        } catch (DomainException | ParseException e) {
            log.error("Error en el proceso de comando {}: {}", this.getClass().getSimpleName(), e.getMessage(), e);
            throw new DomainException(e.getMessage());
        }
    }

    protected abstract C preProcess(C context) throws DomainException, ParseException;

    protected abstract R process(C context) throws DomainException;

    protected R postProcess(R result) {
        return result;
    }
}

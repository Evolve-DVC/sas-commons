package co.com.dvc.commons.cqrs;

import co.com.dvc.commons.exception.DomainException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class QueryAbstract<T, R> {

    public final R execute(T context) throws DomainException {
        try {
            T validatedContext = preProcess(context);
            if (validatedContext == null) {
                log.warn("La consulta {} fue invalidada en el pre-procesamiento", this.getClass().getSimpleName());
                return null;
            }

            R result = process(validatedContext);

            return postProcess(result);

        } catch (DomainException e) {
            log.error("Error de negocio en la consulta {}: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado en la consulta {}: {}", this.getClass().getSimpleName(), e.getMessage(), e);
            throw new DomainException("Error interno al procesar la consulta");
        }
    }

    protected abstract T preProcess(T context) throws DomainException;

    protected abstract R process(T context) throws DomainException;

    protected R postProcess(R result) {
        return result;
    }
}

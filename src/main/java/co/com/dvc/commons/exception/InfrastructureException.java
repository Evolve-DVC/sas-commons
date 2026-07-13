package co.com.dvc.commons.exception;

public class InfrastructureException extends RuntimeException {

    public InfrastructureException() {
        super();
    }

    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(Throwable cause) {
        super(cause);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}

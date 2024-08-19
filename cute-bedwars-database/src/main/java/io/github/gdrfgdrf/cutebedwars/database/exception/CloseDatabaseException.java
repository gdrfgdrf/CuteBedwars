package io.github.gdrfgdrf.cutebedwars.database.exception;

/**
 * @author gdrfgdrf
 */
public class CloseDatabaseException extends RuntimeException {
    public CloseDatabaseException(String message) {
        super(message);
    }

    public CloseDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

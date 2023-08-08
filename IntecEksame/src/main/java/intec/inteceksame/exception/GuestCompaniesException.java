
package intec.inteceksame.exception;

public class GuestCompaniesException extends DatabaseException {
    public GuestCompaniesException(String message) {
        super(message);
    }

    public GuestCompaniesException(String message, Throwable cause) {
        super(message, cause);
    }
}

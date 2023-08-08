package intec.inteceksame.exception;

public class GuestSaveException extends DatabaseException {
    public GuestSaveException(String message) {
        super(message);
    }

    public GuestSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}

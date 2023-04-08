package assessment.backdrop.service.error;

public class UnidentifiedUser extends Exception {
    public UnidentifiedUser (String message) {
        super(String.format(message));
    }
}

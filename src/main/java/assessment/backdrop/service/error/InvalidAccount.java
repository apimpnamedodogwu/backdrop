package assessment.backdrop.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class InvalidAccount extends Exception{
    public InvalidAccount (String message, HttpStatusCode code) {
        super(String.format(message, code));
    }

    public InvalidAccount (String message, HttpStatus status) {
        super(String.format(message, status));
    }
}

package core.maidscc.exceptions;

public class IncorrectDetailsException extends RuntimeException{

    final String message  = "Incorrect Details";

    @Override
    public String getMessage() {
        return this.message;
    }
}

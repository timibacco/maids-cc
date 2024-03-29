package core.maidscc.exceptions;

public class InvalidEmailException extends RuntimeException{

    final String message  = "Invalid Email";

    @Override
    public String getMessage() {
        return this.message;
    }
}

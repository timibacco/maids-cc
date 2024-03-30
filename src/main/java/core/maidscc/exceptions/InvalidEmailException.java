package core.maidscc.exceptions;

public class InvalidEmailException extends RuntimeException{

    final String message  = "Invalid Email Address";

    @Override
    public String getMessage() {
        return this.message;
    }
}

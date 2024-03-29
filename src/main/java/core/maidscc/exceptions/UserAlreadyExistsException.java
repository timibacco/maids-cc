package core.maidscc.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    private final String message = "User with email already exists!";
    @Override
    public String getMessage() {
        return this.message;
    }
}


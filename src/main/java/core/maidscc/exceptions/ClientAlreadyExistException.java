package core.maidscc.exceptions;

public class ClientAlreadyExistException extends RuntimeException{


    public final String message = "Client Already Exists!";
    @Override
    public String getMessage() {
        return this.message;
    }
}

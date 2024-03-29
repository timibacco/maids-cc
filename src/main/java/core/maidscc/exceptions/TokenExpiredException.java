package core.maidscc.exceptions;

public class TokenExpiredException extends  RuntimeException{


    private final String message = "Token is expired.";
    @Override
    public String getMessage() {
        return this.message;
    }
}

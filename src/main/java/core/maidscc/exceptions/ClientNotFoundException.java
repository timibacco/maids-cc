package core.maidscc.exceptions;




public class ClientNotFoundException extends RuntimeException {

    final String message = "Client Not Found!";
    @Override
    public String getMessage(){
        return this.message;

    }
}
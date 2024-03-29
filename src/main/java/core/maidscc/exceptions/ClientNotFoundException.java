package core.maidscc.exceptions;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientNotFoundException extends RuntimeException {

    public final String message = "Client Not Found!";
    @Override
    public String getMessage(){
        return this.message;

    }
}
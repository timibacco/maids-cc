package core.maidscc.exceptions;
import core.maidscc.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final ClientNotFoundException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());

        return ResponseEntity.of(Optional.of(errorResponseDto));
    }


    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final TokenExpiredException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity.of(Optional.of(errorResponseDto));
    }

    @ExceptionHandler(ClientAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final ClientAlreadyExistException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity.of(Optional.of(errorResponseDto));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final UserAlreadyExistsException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity.of(Optional.of(errorResponseDto));
    }

    @ExceptionHandler(IncorrectDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final IncorrectDetailsException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity.of(Optional.of(errorResponseDto));
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final InvalidEmailException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity.of(Optional.of(errorResponseDto));


    }
}
package core.maidscc.dto;
import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime time = LocalDateTime.now();
    
    
}

package picpaydesafiobackend.application.payload.response;

import lombok.*;

import javax.validation.constraints.NotNull;



import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageResponseDTO {

    @NotNull
    private boolean success;
    private String message;
    private Object data;
    private String error;
    @NotNull
    private LocalDate timestamp;
}

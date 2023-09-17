package picpaydesafiobackend.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class MessageResponseDTO {

    private boolean success;
    private String message;
    private Object data;
    private String error;
    private LocalDate timestamp;
}

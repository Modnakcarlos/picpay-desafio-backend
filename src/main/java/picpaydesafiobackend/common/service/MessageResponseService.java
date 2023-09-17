package picpaydesafiobackend.common.service;

import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MessageResponseService {

    public MessageResponseDTO prepareMessageBuild(Boolean sucess, String message, Object data, String error) {
        return MessageResponseDTO.builder()
                .success(sucess)
                .message(message)
                .data(data)
                .error(error)
                .timestamp(LocalDate.now())
                .build();
    }
}

package picpaydesafiobackend.common.payload.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactNumberRequest {
    private String tipoContato;
    private String value;
}

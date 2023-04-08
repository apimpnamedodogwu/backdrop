package assessment.backdrop.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserRequest {
    private String accountName;
    private String accountNumber;
    private String bankCode;
}

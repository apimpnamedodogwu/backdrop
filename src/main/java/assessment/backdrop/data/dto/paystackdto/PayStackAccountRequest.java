package assessment.backdrop.data.dto.paystackdto;


import lombok.Data;

@Data
public class PayStackAccountRequest {
    private String accountName;
    private String accountNumber;
    private String bankCode;

}

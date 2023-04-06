package assessment.backdrop.service.paystack;

import assessment.backdrop.data.dto.paystackdto.PayStackAccountRequest;
import assessment.backdrop.data.dto.paystackdto.PayStackAccountResponse;
import assessment.backdrop.service.error.InvalidAccount;

public interface PayStackService {
    public PayStackAccountResponse resolveUserAccount(String accountNumber, String accountName) throws InvalidAccount;

}

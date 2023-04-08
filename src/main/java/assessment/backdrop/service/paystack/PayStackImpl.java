package assessment.backdrop.service.paystack;

import assessment.backdrop.data.dto.paystackdto.PayStackAccountRequest;
import assessment.backdrop.data.dto.paystackdto.PayStackAccountResponse;
import assessment.backdrop.service.error.InvalidAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.Objects;

@Service
public class PayStackImpl implements PayStackService {
    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    @Override
    public PayStackAccountResponse resolveUserAccount(String accountNumber, String bankCode) throws InvalidAccount {
        try {
            ResponseEntity<PayStackAccountResponse> payStackAccountResponseResponseEntity = WebClient.create("https://api.paystack.co/bank/resolve")
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("account_number", accountNumber)
//                            .queryParam("account_name", accountName)
                            .queryParam("bank_code", bankCode)
                            .queryParam("currency", "NGN")
                            .build())
                    .headers(httpHeaders -> {
                        httpHeaders.set("accept", "application/json");
                        httpHeaders.set("Authorization", "Bearer " + SECRET_KEY);
                    })
                    .exchangeToMono(response -> response.toEntity(PayStackAccountResponse.class))
                    .timeout(Duration.ofMillis(10_000))
                    .block();
            if (payStackAccountResponseResponseEntity != null && payStackAccountResponseResponseEntity.getStatusCode().is2xxSuccessful()) {
                Objects.requireNonNull(payStackAccountResponseResponseEntity.getBody()).setStatus(true);
                return payStackAccountResponseResponseEntity.getBody();
            }
        } catch (WebClientResponseException exception) {
            if (exception.getStatusCode().value() == 400) {
                throw new InvalidAccount("invalid.account.or.code", exception.getStatusCode());
            }
        }

        throw new InvalidAccount("unsuccessful.verification", HttpStatus.BAD_REQUEST);
    }
}

package assessment.backdrop.data.dto.paystackdto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayStackAccountResponse {
    private String message;
    private boolean status;
    private Data data;
    private String accountNumber;
    private String accountName;

    public class Data {
        @JsonAlias("accountNumber")
        @JsonProperty("account_number")
        private String accountNumber;

        @JsonAlias("accountName")
        @JsonProperty("account_name")
        private String accountName;
    }

}

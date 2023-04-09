package assessment.backdrop.service.user;

import assessment.backdrop.data.model.User;
import assessment.backdrop.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FetchVerifiedUser implements DataFetcher<User> {
    private final UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment environment) throws Exception {
        String acctNumber = environment.getArgument("accountNumber");
        String bankCode = environment.getArgument("bankCode");
        return userRepository.findByAccountNumberAndBankCode(acctNumber, bankCode);
    }
}

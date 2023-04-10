package assessment.backdrop.service.user;

import assessment.backdrop.data.dto.UserRequest;
import assessment.backdrop.data.model.User;
import assessment.backdrop.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FetchVerifiedUser implements DataFetcher<String> {
    private final UserServiceImpl userService;

    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {
        String acctNumber = environment.getArgument("accountNumber");
        String bankCode = environment.getArgument("bankCode");
        return userService.verifyUser(UserRequest.builder()
                .accountNumber(acctNumber)
                .bankCode(bankCode)
                .build());
    }
}

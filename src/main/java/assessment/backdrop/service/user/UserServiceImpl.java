package assessment.backdrop.service.user;

import assessment.backdrop.data.dto.UserRequest;
import assessment.backdrop.data.dto.paystackdto.PayStackAccountResponse;
import assessment.backdrop.data.model.User;
import assessment.backdrop.repository.UserRepository;
import assessment.backdrop.service.error.InvalidAccount;
import assessment.backdrop.service.error.UnidentifiedUser;
import assessment.backdrop.service.paystack.PayStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.text.similarity.LevenshteinDistance;


import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PayStackService payStackService;
    private final UserRepository repository;
    private static final int SIMILARITY_THRESHOLD = 3;


    @Override
    public User verifyUser(UserRequest request) throws InvalidAccount, UnidentifiedUser {
        User user = new User();
        PayStackAccountResponse response = payStackService.resolveUserAccount(request.getAccountNumber(), request.getBankCode());
        if (response.isStatus() && Objects.equals(response.getAccountName(), request.getAccountName())) {
            user.setVerified(true);
        }
        if (!Objects.equals(response.getAccountName(), request.getAccountName())) {
            boolean checkedName = checkNameSimilarity(request.getAccountName(), response.getAccountName());
            if (!checkedName) throw new UnidentifiedUser("User " + request.getAccountName() + " cannot be found!");
            else user.setVerified(true);
        }
        return repository.save(user);
    }


    private static boolean checkNameSimilarity(String name1, String name2) {
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        int distance = levenshteinDistance.apply(name1.toLowerCase(), name2.toLowerCase());
        return distance <= SIMILARITY_THRESHOLD;
    }
}



package assessment.backdrop.service.user;

import assessment.backdrop.data.dto.UserRequest;
import assessment.backdrop.data.dto.paystackdto.PayStackAccountResponse;
import assessment.backdrop.data.model.User;
import assessment.backdrop.repository.UserRepository;
import assessment.backdrop.service.error.InvalidAccount;
import assessment.backdrop.service.paystack.PayStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PayStackService payStackService;
    private final UserRepository repository;

    @Override
    public User verifyUser(UserRequest request) throws InvalidAccount {
        User user = new User();
        PayStackAccountResponse response = payStackService.resolveUserAccount(request.getAccountNumber(), request.getAccountName());
        if (response.isStatus())
            user.setVerified(true);
        return repository.save(user);
    }
}

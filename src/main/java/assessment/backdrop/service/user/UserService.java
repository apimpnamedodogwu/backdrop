package assessment.backdrop.service.user;

import assessment.backdrop.data.dto.UserRequest;
import assessment.backdrop.service.error.InvalidAccount;
import assessment.backdrop.service.error.UnidentifiedUser;

public interface UserService {
    String verifyUser(UserRequest request) throws InvalidAccount, UnidentifiedUser;
}

package assessment.backdrop.service.user;

import assessment.backdrop.data.dto.UserRequest;
import assessment.backdrop.data.model.User;
import assessment.backdrop.service.error.InvalidAccount;
import assessment.backdrop.service.error.UnidentifiedUser;

public interface UserService {
    public User verifyUser(UserRequest request) throws InvalidAccount, UnidentifiedUser;
}

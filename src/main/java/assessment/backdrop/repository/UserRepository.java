package assessment.backdrop.repository;

import assessment.backdrop.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByAccountNumberAndBankCode(String accountNumber, String bankCode);
}

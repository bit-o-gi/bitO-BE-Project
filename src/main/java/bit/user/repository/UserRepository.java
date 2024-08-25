package bit.user.repository;

import bit.user.domain.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);

    User save(User user);

}
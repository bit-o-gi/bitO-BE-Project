package bit.user.service;

import bit.couple.domain.Couple;
import bit.user.domain.User;
import bit.user.dto.UserDto;
import java.util.List;

public interface UserService {

    User getById(long id);

    User getByEmail(String email);

    User create(UserDto userDto);

    boolean findByEmail(String email);

    void updateCouple(List<User> users, Couple couple);
}

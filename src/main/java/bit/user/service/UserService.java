package bit.user.service;

import bit.user.domain.User;
import bit.user.dto.UserDto;

public interface UserService {

    User getById(long id);

    User create(UserDto userDto);

}
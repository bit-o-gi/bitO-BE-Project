package bit.user.service;

import bit.couple.domain.Couple;
import bit.user.domain.User;
import bit.user.dto.UserCreateRequest;

public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    User create(UserCreateRequest userCreateRequest);

    boolean findByEmail(String email);

    void updateCouple(String senderEmail, String receiverEmail, Couple couple);
}

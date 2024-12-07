package bit.user.service;

import bit.couple.domain.Couple;
import bit.user.domain.User;
import bit.user.dto.UserDto;
import bit.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public User getById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public User getByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public boolean findByEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

    public User create(UserCreateRequest userCreateRequest) {
        return userRepository.save(User.from(userCreateRequest));
    }

  @Transactional
  public void updateCouple(String senderEmail, String receiverEmail, Couple couple) {
    User sender = userRepository.findByEmail(senderEmail)
        .orElseThrow(() -> new IllegalArgumentException("User not found By Email"));
    User receiver = userRepository.findByEmail(receiverEmail)
        .orElseThrow(() -> new IllegalArgumentException("User not found By Email"));

    for (User user : List.of(sender, receiver)) {
      User updateCoupleUser = user.updateCouple(couple);
      userRepository.save(updateCoupleUser);
    }
  }
}

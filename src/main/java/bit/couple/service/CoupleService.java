package bit.couple.service;

import bit.couple.domain.Couple;
import bit.couple.dto.CoupleCommand;
import bit.couple.exception.CoupleException.CoupleNotFoundException;
import bit.couple.repository.CoupleRepository;
import bit.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoupleService {

  private final CoupleRepository coupleRepository;
  private final UserService userService;

  @Transactional
  public void createCouple(CoupleCommand command) {
    // ToDo. 도메인에 중복 처리 추가
    Couple savedCouple = coupleRepository.save(Couple.of());
    userService.updateCouple(command.getUserIds(), savedCouple);
  }

  public void approveCouple(Long coupleId) {
    Couple couple = coupleRepository.findById(coupleId).orElseThrow(CoupleNotFoundException::new);
    couple.approve();
    coupleRepository.save(couple);
  }

  public void deleteCouple(Long coupleId) {
    if (!coupleRepository.existsById(coupleId)) {
      throw new CoupleNotFoundException();
    }
    // TODO: 바로 지우는 대신 스케줄링 등록
    coupleRepository.deleteById(coupleId);
  }
}

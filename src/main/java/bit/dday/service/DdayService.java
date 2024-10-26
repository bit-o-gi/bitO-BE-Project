package bit.dday.service;

import bit.couple.domain.Couple;
import bit.couple.exception.CoupleException.CoupleNotFoundException;
import bit.couple.repository.CoupleRepository;
import bit.dday.domain.Dday;
import bit.dday.dto.DdayCommand;
import bit.dday.exception.DdayException.DdayNotFoundException;
import bit.dday.repository.DdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DdayService {

    private final DdayRepository ddayRepository;
    private final CoupleRepository coupleRepository;

    public Dday getDday(Long id) {
        return ddayRepository.findById(id).orElseThrow(DdayNotFoundException::new);
    }

    public Dday createDday(DdayCommand command) {
        Couple couple = coupleRepository.findByUsersId(command.userId).orElseThrow(CoupleNotFoundException::new);
        Dday dday = Dday.builder()
                .couple(couple)
                .title(command.title)
                .targetDate(command.targetDate)
                .build();
        return ddayRepository.save(dday);
    }

    public Dday updateDday(Long id, DdayCommand ddayCommand) {
        Dday dday = ddayRepository.findById(id).orElseThrow(DdayNotFoundException::new);
        dday.update(ddayCommand);
        return ddayRepository.save(dday);
    }

    public void deleteDday(Long id) {
        if (!ddayRepository.existsById(id)) {
            throw new DdayNotFoundException();
        }
        ddayRepository.deleteById(id);
    }
}

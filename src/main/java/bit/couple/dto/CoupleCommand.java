package bit.couple.dto;

import bit.couple.domain.Couple;
import bit.dday.dto.BaseCommand;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoupleCommand implements BaseCommand<Couple> {

  private final String sender;
  private final String receiver;

}

package bit.couple.dto;

import bit.couple.domain.Couple;
import bit.dday.dto.BaseRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CoupleRequest implements BaseRequest<Couple> {

  private final List<Long> userIds = new ArrayList<>();

  @Override
  public CoupleCommand toCommand() {
    return new CoupleCommand(userIds);
  }
}

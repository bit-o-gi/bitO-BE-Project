package bit.couple.dto;

import bit.couple.domain.Couple;
import bit.dday.dto.BaseRequest;
import bit.user.domain.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoupleRequest implements BaseRequest<Couple> {
    private List<User> users;

    @Override
    public CoupleCommand toCommand() {
        return new CoupleCommand(users);
    }
}

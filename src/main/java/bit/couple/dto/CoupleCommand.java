package bit.couple.dto;

import bit.couple.domain.Couple;
import bit.dday.dto.BaseCommand;
import bit.user.domain.User;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoupleCommand implements BaseCommand<Couple> {
    private final List<User> users;


}

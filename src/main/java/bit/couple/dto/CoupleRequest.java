package bit.couple.dto;

import bit.base.BaseRequest;
import bit.couple.domain.Couple;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CoupleRequest implements BaseRequest<Couple> {

    private final String senderEmail;
    private final String receiverEmail;

    @Override
    public CoupleCommand toCommand() {
        return new CoupleCommand(senderEmail, receiverEmail);
    }
}

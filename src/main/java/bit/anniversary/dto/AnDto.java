package bit.anniversary.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bit.anniversary.entity.Anniversary;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter // TODO : 현재 파악한 소스로 graphql 에는 Setter 을 사용해야 된다.-> 추후 이유 찾아봄
public class AnDto {

    private Long id;
    private String writeTime;
    private String title;
    private String writer;
    private String withPeople;
    private String updateTime;
    private String content;
    private LocalDateTime anniversaryDate;

    // NOTE: DTO에서 Entity로 변환하는 메서드
    public Anniversary creatAnniversary(ModelMapper modelMapper) {
        return modelMapper.map(this, Anniversary.class);
    }

    // NOTE: DTO에서 Response DTO로 변환하는 메서드
    public AnResDto createAnReqDto(ModelMapper modelMapper) {
        return modelMapper.map(this, AnResDto.class);
    }

    // NOTE: Entity에서 DTO로 변환하는 메서드 (정적 메서드로 정의)
    public static AnDto of(Anniversary anniversary, ModelMapper modelMapper) {
        return modelMapper.map(anniversary, AnDto.class);
    }
}

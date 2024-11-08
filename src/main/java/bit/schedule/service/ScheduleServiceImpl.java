package bit.schedule.service;

import bit.schedule.domain.Schedule;
import bit.schedule.dto.ScheduleCreateRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.dto.ScheduleUpdateRequest;
import bit.schedule.exception.ScheduleNotFoundException;
import bit.schedule.repository.ScheduleRepository;
import bit.user.entity.UserEntity;
import bit.user.repository.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public ScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return new ScheduleResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> getSchedulesByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId)
                .stream()
                .map(ScheduleResponse::new)
                .toList();
    }

    @Override
    public List<ScheduleResponse> getSchedulesByCoupleId(Long coupleId) {
        return scheduleRepository.findByUserCoupleId(coupleId)
                .stream()
                .map(ScheduleResponse::new)
                .toList();
    }

    @Transactional
    @Override
    public ScheduleResponse saveSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        UserEntity userEntity = userJpaRepository.findById(scheduleCreateRequest.getUserId()).orElseThrow(EntityNotFoundException::new);
        Schedule schedule = scheduleCreateRequest.toEntity(userEntity);
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = findScheduleById(scheduleId);
        schedule.update(scheduleUpdateRequest);
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponse deleteSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        scheduleRepository.deleteById(scheduleId);
        return new ScheduleResponse(schedule);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);
    }
}

package bit.schedule.service;

import bit.schedule.domain.Schedule;
import bit.schedule.dto.ScheduleRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.exception.ScheduleNotFoundException;
import bit.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

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

    @Transactional
    @Override
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRequest.toEntity();
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest) {
        Schedule schedule = findScheduleById(scheduleId);
        schedule.update(scheduleRequest);
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

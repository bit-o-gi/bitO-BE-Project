package bit.schedule.service;

import bit.schedule.dto.ScheduleCreateRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.dto.ScheduleUpdateRequest;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse getSchedule(Long scheduleId);

    List<ScheduleResponse> getSchedulesByUserId(Long userId);

    List<ScheduleResponse> getSchedulesByCoupleId(Long coupleId);

    ScheduleResponse saveSchedule(ScheduleCreateRequest scheduleCreateRequest);

    ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest);

    ScheduleResponse deleteSchedule(Long scheduleId);
}

package bit.schedule.controller;

import bit.schedule.dto.ScheduleCreateRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.dto.ScheduleUpdateRequest;
import bit.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{scheduleId}")
    public ScheduleResponse getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/user/{userId}")
    public List<ScheduleResponse> getScheduleByUser(@PathVariable Long userId) {
        return scheduleService.getSchedulesByUserId(userId);
    }

    @GetMapping("/couple/{coupleId}")
    public List<ScheduleResponse> getScheduleByCouple(@PathVariable Long coupleId) {
        return scheduleService.getSchedulesByCoupleId(coupleId);
    }

    @PostMapping("")
    public ScheduleResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        return scheduleService.saveSchedule(scheduleCreateRequest);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        return scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest);
    }

    @DeleteMapping("/{scheduleId}")
    public ScheduleResponse deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}

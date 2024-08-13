package bit.schedule.controller;

import bit.schedule.dto.ScheduleRequest;
import bit.schedule.service.ScheduleService;
import bit.schedule.domain.Schedule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/api/v1/schedules/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.getSchedule(id);

        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/api/v1/schedules/new")
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule createdSchedule = scheduleService.save(scheduleRequest);

        return ResponseEntity.ok(createdSchedule);
    }

    @PatchMapping("/api/v1/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id,
                                                   @Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule updatedSchedule = scheduleService.patch(id, scheduleRequest);

        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/api/v1/schedules/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        Schedule deletedSchedule = scheduleService.delete(id);

        return ResponseEntity.ok(deletedSchedule);
    }

}
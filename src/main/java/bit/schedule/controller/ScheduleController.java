package bit.schedule.controller;

import bit.schedule.dto.ScheduleCreateRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.dto.ScheduleUpdateRequest;
import bit.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "스케줄 조회 API", description = "스케줄을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    public ScheduleResponse getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자별 스케줄 조회 API", description = "사용자별 스케줄을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    public List<ScheduleResponse> getScheduleByUser(@PathVariable Long userId) {
        return scheduleService.getSchedulesByUserId(userId);
    }

    @GetMapping("/couple/{coupleId}")
    @Operation(summary = "커플별 스케줄 조회 API", description = "커플별 스케줄을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
            }
    )
    public List<ScheduleResponse> getScheduleByCouple(@PathVariable Long coupleId) {
        return scheduleService.getSchedulesByCoupleId(coupleId);
    }

    @PostMapping("")
    @Operation(summary = "스케줄 생성 API", description = "스케줄을 생성합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "잘못된 요청"),
            }
    )
    public ScheduleResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        return scheduleService.saveSchedule(scheduleCreateRequest);
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "스케줄 수정 API", description = "스케줄을 수정합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "해당 스케줄이 존재하지 않습니다."),
            }
    )
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        return scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "스케줄 삭제 API", description = "스케줄을 삭제합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "해당 스케줄이 존재하지 않습니다."),
            }
    )
    public ScheduleResponse deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}

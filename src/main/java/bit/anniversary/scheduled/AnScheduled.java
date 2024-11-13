package bit.anniversary.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bit.anniversary.service.AnService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AnScheduled {

	private final AnService anService;

	/**
	 * 매일 아침 9시에 실행되어 기념일 알림을 전송합니다.
	 */
	@Scheduled(cron = "0 0 9 * * ?")
	@Transactional(readOnly = true)
	public void sendAnniversaryNotifications() {
		// anService.sendAnniversaryNotification();
	}
}

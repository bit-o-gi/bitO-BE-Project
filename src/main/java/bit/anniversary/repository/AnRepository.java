package bit.anniversary.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bit.anniversary.entity.Anniversary;

@Repository
public interface AnRepository extends JpaRepository<Anniversary,Long> {

	// NOTE: 특정 날짜 범위 안에 있는 기념일들을 조회
	List<Anniversary> findAllByAnniversaryDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	// NOTE: 특정 날짜 이후의 기념일 조회
	List<Anniversary> findAllByAnniversaryDateAfter(LocalDateTime date);

	// NOTE: 특정 날짜 이전의 기념일 조회
	List<Anniversary> findAllByAnniversaryDateBefore(LocalDateTime date);

}

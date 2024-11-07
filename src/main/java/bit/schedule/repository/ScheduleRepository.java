package bit.schedule.repository;

import bit.schedule.domain.Schedule;
import bit.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUser(UserEntity userEntity);

    List<Schedule> findByUserIn(List<UserEntity> userEntities);
}

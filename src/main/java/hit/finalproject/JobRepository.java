package hit.finalproject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

/* Connecting to database interface */
public interface JobRepository extends JpaRepository <Job,Long>
{
    List<Job> findByStatus(String status);
    List<Job> findByJobType(String jobType);
    List<Job> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}

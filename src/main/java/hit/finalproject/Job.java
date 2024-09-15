package hit.finalproject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name="job")
@Data
@NoArgsConstructor
public class Job
{
    /* Fields */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobName;
    private String jobType;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private String password;             // PASSWORD!!!

    /* Constructor */
    public Job(String jobName, String jobType, String status, LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        this.jobName = jobName;
        this.jobType = jobType;
        this.status  = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        //this.password = password;
    }
}

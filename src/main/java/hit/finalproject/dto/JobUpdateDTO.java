package hit.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobUpdateDTO
{
    private String jobName;
    private String jobType;
    private String status;
    //private String password;
    private LocalDateTime updatedAt;
}

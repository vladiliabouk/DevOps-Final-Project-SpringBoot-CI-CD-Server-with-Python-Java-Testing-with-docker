package hit.finalproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobCreateDTO
{
    private String jobName;
    private String jobType;
    private String status;
    //private String password;
}
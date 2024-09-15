package hit.finalproject;

import hit.finalproject.dto.JobCreateDTO;
import hit.finalproject.dto.JobUpdateDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

@WebMvcTest(JobController.class)
public class JobControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    public void testCreateJob() throws Exception
    {
        JobCreateDTO jobCreateDTO = new JobCreateDTO("Job1", "Type1", "Status1");
        Job job = new Job("Job1", "Type1", "Status1", LocalDateTime.now(), LocalDateTime.now());
        when(jobService.saveJob(any(Job.class))).thenReturn(job);

        mockMvc.perform(post("/jobs")
                        .contentType("application/json")
                        .content("{\"jobName\":\"Job1\",\"jobType\":\"Type1\",\"status\":\"Status1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.jobName").value("Job1"))
                .andExpect(jsonPath("$.jobType").value("Type1"));
    }

    @Test
    public void testUpdateJob() throws Exception
    {
        JobUpdateDTO jobUpdateDTO = new JobUpdateDTO("UpdatedJob", "UpdatedType", "UpdatedStatus", LocalDateTime.now());
        Job job = new Job("UpdatedJob", "UpdatedType", "UpdatedStatus", LocalDateTime.now(), LocalDateTime.now());
        when(jobService.updateJob(eq(1L), any(Job.class))).thenReturn(job);

        mockMvc.perform(MockMvcRequestBuilders.put("/jobs/1")
                        .contentType("application/json")
                        .content("{\"jobName\":\"UpdatedJob\",\"jobType\":\"UpdatedType\",\"status\":\"UpdatedStatus\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobName").value("UpdatedJob"))
                .andExpect(jsonPath("$.jobType").value("UpdatedType"));
    }
}

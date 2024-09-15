package hit.finalproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

public class JobServiceParameterizedTest
{
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "Type1, Status1",
            "Type2, Status2",
            "Type3, Status3"
    })
    public void testAddJobWithVariousStatuses(String jobType, String status)
    {
        Job job = new Job("Job", jobType, status, LocalDateTime.now(), LocalDateTime.now());
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job savedJob = jobService.saveJob(job);

        assertNotNull(savedJob);
        assertEquals(jobType, savedJob.getJobType());
        assertEquals(status, savedJob.getStatus());
    }
}

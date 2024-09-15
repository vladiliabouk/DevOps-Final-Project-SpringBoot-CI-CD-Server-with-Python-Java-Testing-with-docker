package hit.finalproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;

public class JobServiceExceptionTest
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

    @Test
    public void testDeleteNonExistentJob()
    {
        doThrow(new RuntimeException("Job not found")).when(jobRepository).deleteById(999L);

        assertThrows(RuntimeException.class, () -> jobService.deleteJob(999L));
    }

    @Test
    public void testUpdateJobWithInvalidData()
    {
        when(jobRepository.findById(1L)).thenReturn(Optional.empty());

        Job invalidJob = new Job("InvalidJob", "InvalidType", "InvalidStatus", LocalDateTime.now(), LocalDateTime.now());

        assertThrows(RuntimeException.class, () -> jobService.updateJob(1L, invalidJob));
    }
}

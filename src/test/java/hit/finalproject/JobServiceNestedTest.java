package hit.finalproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class JobServiceNestedTest
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

    @Nested
    class CreateAndUpdateTests
    {
        @Test
        public void testSaveJob()
        {
            Job job = new Job("Job1", "Type1", "Status1", LocalDateTime.now(), LocalDateTime.now());
            when(jobRepository.save(any(Job.class))).thenReturn(job);

            Job savedJob = jobService.saveJob(job);

            assertNotNull(savedJob);
            assertEquals("Job1", savedJob.getJobName());
        }

        @Test
        public void testUpdateJob()
        {
            Job job = new Job("Job1", "Type1", "Status1", LocalDateTime.now(), LocalDateTime.now());
            when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
            when(jobRepository.save(any(Job.class))).thenReturn(job);

            Job updatedJob = new Job("UpdatedJob", "UpdatedType", "UpdatedStatus", LocalDateTime.now(), LocalDateTime.now());
            Job result = jobService.updateJob(1L, updatedJob);

            assertNotNull(result);
            assertEquals("UpdatedJob", result.getJobName());
        }
    }

    @Nested
    class DeleteAndRetrieveTests
    {
        @Test
        public void testDeleteJob()
        {
            doNothing().when(jobRepository).deleteById(1L);

            assertDoesNotThrow(() -> jobService.deleteJob(1L));
            verify(jobRepository, times(1)).deleteById(1L);
        }

        @Test
        public void testGetJobById()
        {
            Job job = new Job("Job1", "Type1", "Status1", LocalDateTime.now(), LocalDateTime.now());
            when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

            Optional<Job> retrievedJob = jobService.getJobById(1L);

            assertTrue(retrievedJob.isPresent());
            assertEquals("Job1", retrievedJob.get().getJobName());
        }
    }
}


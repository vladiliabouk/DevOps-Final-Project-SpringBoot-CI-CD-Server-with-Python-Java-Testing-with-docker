package hit.finalproject;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JobService
{
    /* Layer to database */
    private final JobRepository jobRepository;

    /* Logger instance */
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    //private final passwordEncoder;

    /* Constructor */
    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    /*
     * Create a new job to database
     * @param job entity to save
     * @return saved job if successful
     */
    public Job saveJob(Job job)
    {
        logger.info("Creating a new job: {}", job.getJobName());
        // Encoding of password before saving:
        // String encodedPassword = passwordEncoder.encode(job.getPassword());
        //job.setPassword(encodedPassword);
        Job newJob = new Job();

        newJob.setJobName(job.getJobName());
        newJob.setJobType(job.getJobType());
        newJob.setStatus(job.getStatus());
        newJob.setCreatedAt(LocalDateTime.now());
        newJob.setUpdatedAt(LocalDateTime.now());

        Job savedJob = jobRepository.save(newJob);
        logger.info("Job created successfully with ID: {}", savedJob.getId());

        return savedJob;
    }

    /*
     * Update job
     * @param id and job details for changing
     * @return changed job
     */
    public Job updateJob(Long id, Job jobDetails)
    {
        logger.info("Updating job with ID: {}", id);

        Job job = jobRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Job not found"));

        job.setJobName(jobDetails.getJobName());
        job.setJobType(jobDetails.getJobType());
        job.setStatus(jobDetails.getStatus());
        job.setUpdatedAt(jobDetails.getUpdatedAt());

        Job updatedJob = jobRepository.save(job);
        logger.info("Job with ID: {} updated successfully", updatedJob.getId());

        return updatedJob;
    }

    /*
     * Delete job
     * @param id of job to deleting
     * Delete job by getting id
     * @return void
     */
    public void deleteJob(Long id)
    {
        logger.warn("Deleting job with ID: {}", id);
        jobRepository.deleteById(id);
    }

    /*
     * Getting list of all jobs
     * @return list of jobs
     */
    public List<Job> getAllJobs()
    {
        logger.info("Fetching all jobs");
        return jobRepository.findAll();
    }

    /*
     * Getting job by specific id
     * @param id of job
     * @return job
     * Optional for if we get 'null'
     */
    public Optional<Job> getJobById(Long id)
    {
        logger.info("Fetching job with ID: {}", id);
        return jobRepository.findById(id);
    }

    /*
     * Getting list of jobs by specific status
     * @param status of job
     * @return list of jobs with specific status
     */
    public List<Job> getJobsByStatus(String status)
    {
        logger.info("Fetching jobs with status: {}", status);
        return jobRepository.findByStatus(status);
    }

    /*
     * Getting list of jobs by specific type
     * @param type of job
     * @return list of jobs with specific type
     */
    public List<Job> getJobsByJobType(String jobType)
    {
        logger.info("Fetching jobs with job type: {}", jobType);
        return jobRepository.findByJobType(jobType);
    }

    /*
     * Getting list of jobs that created between two dates
     * @param start date and end date
     * @return list of job in this range
     */
    public List<Job> getJobsByDateRange(LocalDateTime startDate, LocalDateTime endDate)
    {
        logger.info("Fetching jobs created between {} and {}", startDate, endDate);
        return jobRepository.findByCreatedAtBetween(startDate, endDate);
    }
}

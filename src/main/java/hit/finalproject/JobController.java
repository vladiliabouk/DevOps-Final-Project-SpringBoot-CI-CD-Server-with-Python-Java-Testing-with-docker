package hit.finalproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import hit.finalproject.dto.JobCreateDTO;
import hit.finalproject.dto.JobUpdateDTO;



@Controller
//@RestController
@RequestMapping
public class JobController
{
    private final JobService jobService;

    /* Logger instance */
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    /* Constructor */
    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }


    /*-------------------------------------------- GET --------------------------------------------*/
    /*
     * Gets all the jobs in the Job table using jobService
     * @return an HTTP Response including a JSON with an Array of JSONs
     * representing all the jobs in our database
     * Successful requests results in status code 200
     */
    //@GetMapping("/jobs")
    //public ResponseEntity<List<Job>> getAllJobs(Model model)
    //{
        //logger.info("Fetching all jobs");
        //List<Job> jobs = jobService.getAllJobs();

        //model.addAttribute("jobs", jobs);

        //logger.info("Successfully fetched all jobs");
        //return ResponseEntity.ok(jobs);*/

    //}


    /*
     * Gets all the jobs in the Job table by ID
     * @return an HTTP Response including a JSON
     */
    /*
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id)
    {
        logger.info("Fetching job with ID: {}", id);
        return jobService.getJobById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    */
    /*
     * Gets all the jobs in the Job table by STATUS
     * @return an HTTP Response including a JSON with an Array of JSONs
     * Successful requests results in status code 200
     */
    /*
    @GetMapping("/jobs/status/{status}")
    public ResponseEntity<List<Job>> getJobsByStatus(@PathVariable String status)
    {
        logger.info("Fetching jobs with status: {}", status);
        List<Job> jobs = jobService.getJobsByStatus(status);

        logger.info("Successfully fetched jobs with status: {}", status);
        return ResponseEntity.ok(jobs);
    }
    */
    /*
     * Gets all the jobs in the Job table by JOB TYPE
     * @return an HTTP Response including a JSON with an Array of JSONs
     * Successful requests results in status code 200
     */
    /*
    @GetMapping("/jobs/jobType/{jobType}")
    public ResponseEntity<List<Job>> getJobsByJobType(@PathVariable String jobType)
    {
        logger.info("Fetching jobs with job type: {}", jobType);
        List<Job> jobs = jobService.getJobsByJobType(jobType);

        logger.info("Successfully fetched jobs with job type: {}", jobType);
        return ResponseEntity.ok(jobs);
    }
    */
    /*
     * Gets all the jobs in the Job table, created between two dates
     * @param two dates
     * @return an HTTP Response including a JSON with an Array of JSONs
     * Successful requests results in status code 200
     * Example: http://localhost:8080/jobs/date-range?startDate=2024-01-01T00:00:00&endDate=2024-12-31T23:59:59
     */
    /*
    @GetMapping("/jobs/date-range")
    public ResponseEntity<List<Job>> getJobsByDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate)
    {
        logger.info("Fetching jobs created between {} and {}", startDate, endDate);
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        List<Job> jobs = jobService.getJobsByDateRange(start, end);
        logger.info("Successfully fetched jobs in date range");
        return ResponseEntity.ok(jobs);
    }
    */


    /*-------------------------------------------- POST --------------------------------------------*/
    /*
     * Creates a new job in the database.
     * This method accepts a Job object in the request body,
     * saves it to the database, and returns the saved Job
     * along with a URI of the newly created resource
     *
     * @param job the Job object to be created
     * @return a ResponseEntity containing the created Job
     * and a location header with the URI of the new resource
     * {
            "jobName": "name",
            "jobType": "type",
            "status": "Success",
            "createdAt": "2024-08-27T10:58:21.865184",
            "updatedAt": "2024-08-27T10:58:21.865184"
        }
     */
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job)
    {
        logger.info("Creating new job: {}", job.getJobName());
        Job newJob = jobService.saveJob(job);
        URI location = URI.create("/api/jobs/" + newJob.getId());

        logger.info("Successfully created job with ID: {}", newJob.getId());
        return ResponseEntity.created(location).body(newJob);
    }


    /*-------------------------------------------- PUT --------------------------------------------*/

    /*
     * http://localhost:8080/jobs/{id that we want to change}
     * {
            "jobName": "Anton",
            "jobType": "Build",
            "status": "Completed",
            "createdAt": "2024-08-27T10:58:21.865184",
            "updatedAt": "2024-08-28T10:58:21.865184"
        }
     */
    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job)
    {
        logger.info("Updating job with ID: {}", id);
        Job updatedJob = jobService.updateJob(id,job);

        logger.info("Successfully updated job with ID: {}", updatedJob.getId());
        return ResponseEntity.ok(updatedJob);
    }



    /*-------------------------------------------- DELETE --------------------------------------------*/
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id)
    {
        logger.warn("Deleting job with ID: {}", id);
        jobService.deleteJob(id);
        //return ResponseEntity.ok().build();
        logger.info("Successfully deleted job with ID: {}", id);
        return ResponseEntity.noContent().build();
    }



    /*-------------------------------------------- GET with HTML --------------------------------------------*/
    @GetMapping("/jobs")
    public String getAllJobs(Model model)
    {
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobs"; // Имя HTML-шаблона без расширения
    }

    @GetMapping("/jobs/{id}")
    public String getJobById(@PathVariable Long id, Model model)
    {
        logger.info("Fetching job with ID: {}", id);
        return jobService.getJobById(id)
                .map(job -> {
                    model.addAttribute("job", job);
                    return "jobDetail"; // Имя HTML-шаблона для отображения деталей задачи
                })
                .orElse("404"); // Имя шаблона для страницы 404 (если задача не найдена)
    }

    @GetMapping("/jobs/status/{status}")
    public String getJobsByStatus(@PathVariable String status, Model model)
    {
        logger.info("Fetching jobs with status: {}", status);
        List<Job> jobs = jobService.getJobsByStatus(status);
        model.addAttribute("jobs", jobs);
        model.addAttribute("status", status);
        return "jobsByStatus"; // Имя HTML-шаблона для отображения задач по статусу
    }

    @GetMapping("/jobs/jobType/{jobType}")
    public String getJobsByJobType(@PathVariable String jobType, Model model)
    {
        logger.info("Fetching jobs with job type: {}", jobType);
        List<Job> jobs = jobService.getJobsByJobType(jobType);
        model.addAttribute("jobs", jobs);
        model.addAttribute("jobType", jobType);
        return "jobsByJobType"; // Имя HTML-шаблона для отображения задач по типу работы
    }

    @GetMapping("/jobs/date-range")
    public String getJobsByDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Model model)
    {
        logger.info("Fetching jobs created between {} and {}", startDate, endDate);
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        List<Job> jobs = jobService.getJobsByDateRange(start, end);
        model.addAttribute("jobs", jobs);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "jobsByDateRange"; // Имя HTML-шаблона для отображения задач в диапазоне дат
    }

}

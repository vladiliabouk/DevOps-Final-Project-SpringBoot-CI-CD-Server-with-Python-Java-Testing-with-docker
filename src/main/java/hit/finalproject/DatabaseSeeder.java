package hit.finalproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class DatabaseSeeder
{
    /* Logger: */
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    /*
     * Initializing our database
     * Creating 3 jobs in database
     * CommandLineRunner - for creating objects at running time
     */
    @Bean
    CommandLineRunner initDatabase(JobRepository jobRepository)
    {
        return args -> {
            logger.info("Initializing database in runtime...Server is running");

            Job job1 = new Job("Build Pipeline", "Build", "Success", LocalDateTime.now().minusDays(1), LocalDateTime.now());
            Job job2 = new Job("Deploy to Production", "Deploy", "Failed", LocalDateTime.now().minusDays(2), LocalDateTime.now());
            Job job3 = new Job("Test Automation", "Test", "Running", LocalDateTime.now().minusHours(5), LocalDateTime.now());

            logger.info("Created job: " + jobRepository.save(job1));
            logger.info("Created job: " + jobRepository.save(job2));
            logger.info("Created job: " + jobRepository.save(job3));
        };
    }
}

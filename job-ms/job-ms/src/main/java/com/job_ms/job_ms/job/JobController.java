package com.job_ms.job_ms.job;


import com.job_ms.job_ms.job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job savedJob = jobService.addJob(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> retrieveAllJobs(){
        List<JobDTO> savedJobs = jobService.findAllJobs();
        return new ResponseEntity<>(savedJobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> retrieveJobById(@PathVariable Long id){
        JobDTO job = jobService.findJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> DeleteJob(@PathVariable Long id){
        Job job = jobService.deleteJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> UpdateJob(@PathVariable Long id, @RequestBody Job job){
        Job updatedJob = jobService.updateJob(id, job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }
}

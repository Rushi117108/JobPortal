package com.rushi.jobportal.service;

import com.rushi.jobportal.model.JobPostActivity;
import com.rushi.jobportal.repository.JobPostActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPostActivityService {

    @Autowired
    JobPostActivityRepo jobPostActivityRepo;

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepo.save(jobPostActivity);
    }

}

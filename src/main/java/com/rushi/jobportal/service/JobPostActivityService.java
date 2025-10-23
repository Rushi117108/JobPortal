package com.rushi.jobportal.service;

import com.rushi.jobportal.dto.RecruiterjobsDto;
import com.rushi.jobportal.model.JobCompany;
import com.rushi.jobportal.model.JobLocation;
import com.rushi.jobportal.model.JobPostActivity;
import com.rushi.jobportal.repository.JobPostActivityRepo;
import com.rushi.jobportal.model.RecruiterJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    @Autowired
    JobPostActivityRepo jobPostActivityRepo;

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepo.save(jobPostActivity);
    }

    public List<RecruiterjobsDto> getJobs(int recruiterId){
        List<RecruiterJobs> recruiterJobs = jobPostActivityRepo.getRecruiterJobs(recruiterId);
        List<RecruiterjobsDto> recruiterjobsDtos = new ArrayList<>();

        for(RecruiterJobs rec: recruiterJobs){
            JobLocation jobLocation = new JobLocation(rec.getCountry(), rec.getState(), rec.getCity(), rec.getLocationId());
            JobCompany jobCompany = new JobCompany(rec.getCompanyId(), "", rec.getName());
            RecruiterjobsDto recruiterjobsDto = new RecruiterjobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), jobCompany, jobLocation);
            recruiterjobsDtos.add(recruiterjobsDto);
        }
        return recruiterjobsDtos;
    }

    public JobPostActivity getActivity(Integer id) {
        return jobPostActivityRepo.findById(id).orElseThrow(()->new RuntimeException("Job not found"));
    }
}

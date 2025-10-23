package com.rushi.jobportal.dto;

import com.rushi.jobportal.model.JobCompany;
import com.rushi.jobportal.model.JobLocation;

import java.net.Inet4Address;

public class RecruiterjobsDto {

    private Long totalCandidates;
    private Integer jobPostId;
    private String jobTitle;
    private JobCompany jobCompanyId;
    private JobLocation jobLocationId;

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobCompany getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(JobCompany jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public JobLocation getJobLocationId() {
        return jobLocationId;
    }

    public void setJobLocationId(JobLocation jobLocationId) {
        this.jobLocationId = jobLocationId;
    }

    public RecruiterjobsDto(Long totalCandidates, Integer jobPostId, String jobTitle, JobCompany jobCompanyId, JobLocation jobLocationId) {
        this.totalCandidates = totalCandidates;
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.jobCompanyId = jobCompanyId;
        this.jobLocationId = jobLocationId;
    }

    public RecruiterjobsDto() {
    }
}

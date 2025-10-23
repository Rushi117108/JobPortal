package com.rushi.jobportal.controller;

import com.rushi.jobportal.model.JobPostActivity;
import com.rushi.jobportal.service.JobPostActivityService;
import com.rushi.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobSeekerApplyController {

    @Autowired
    private JobPostActivityService jobPostActivityService;

    @Autowired
    private UserService userService;


    @GetMapping("/job-details-apply/{id}")
    public String display(@PathVariable("id") Integer id, Model model){
        JobPostActivity jobDetails = jobPostActivityService.getActivity(id);

        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", userService.getCurrentUserProfile());
        return "job-details";
    }

}

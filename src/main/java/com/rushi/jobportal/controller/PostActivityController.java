package com.rushi.jobportal.controller;

import com.rushi.jobportal.dto.RecruiterjobsDto;
import com.rushi.jobportal.model.JobPostActivity;
import com.rushi.jobportal.model.RecruiterProfile;
import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.service.JobPostActivityService;
import com.rushi.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class PostActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobPostActivityService jobPostActivityService;

    @GetMapping("/dashboard/")
    public String searchJobs(Model model){
        Object currentUserProfile = userService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            model.addAttribute("username", userName);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                List<RecruiterjobsDto> recruiterjobsDtos = jobPostActivityService.getJobs(((RecruiterProfile)currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost", recruiterjobsDtos);
            }
        }
        model.addAttribute("user", currentUserProfile);
        return "dashboard";
    }

    @GetMapping("/dashboard/add")
    public String addJobs(Model model){
        model.addAttribute("jobPostActivity", new JobPostActivity());
        model.addAttribute("user", userService.getCurrentUserProfile());
        return "add-jobs";
    }

    @PostMapping("/dashboard/addNew")
    public String addNewJob(JobPostActivity jobPostActivity, Model model ){
        Users user = userService.getCurrentUser();
        if(user != null){
            jobPostActivity.setPostedById(user);
        }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity", jobPostActivity);
        jobPostActivityService.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }

    @PostMapping("/dashboard/edit/{id}")
    public String editJobDetails(@PathVariable("id") Integer id, Model model){
        JobPostActivity jobDetails = jobPostActivityService.getActivity(id);
        model.addAttribute("jobPostActivity", jobDetails);
        model.addAttribute("user", userService.getCurrentUserProfile());
        return "add-jobs";
    }
}

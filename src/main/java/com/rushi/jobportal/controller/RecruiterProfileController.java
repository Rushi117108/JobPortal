package com.rushi.jobportal.controller;

import com.rushi.jobportal.model.RecruiterProfile;
import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.repository.UsersRepository;
import com.rushi.jobportal.service.RecruiterProfileService;
import com.rushi.jobportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RecruiterProfileService recruiterProfileService;


    @GetMapping("/")
    public String recruiterProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserName = authentication.getName();
            Users user =  usersRepository.findByEmail(currentUserName).orElseThrow(()->
                    new UsernameNotFoundException("Could not found user with user name "+ currentUserName));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getRecruiterProfile(user.getUserId());

            if(!recruiterProfile.isEmpty()){
                model.addAttribute("profile",recruiterProfile.get());
            }
        }

        return "recruiter_profile";
    }

    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image")MultipartFile multipartFile, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Users user = usersRepository.findByEmail(currentUserName).orElseThrow(() ->
                    new UsernameNotFoundException("Could not found user with user name " + currentUserName));
            recruiterProfile.setUserId(user);
            recruiterProfile.setUserAccountId(user.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String fileName = "";

        if (!multipartFile.getOriginalFilename().equals("")) {
            fileName = multipartFile.getOriginalFilename();
            recruiterProfile.setProfilePhoto(fileName);
        }
        RecruiterProfile updateUser = recruiterProfileService.addUser(recruiterProfile);
        String uploadPath = "photos/recruiter/" + updateUser.getUserAccountId();
        try {
            FileUploadUtil.uploadFile(uploadPath, fileName, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/";
    }
}

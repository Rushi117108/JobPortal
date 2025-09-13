package com.rushi.jobportal.service;

import com.rushi.jobportal.model.JobSeekerProfile;
import com.rushi.jobportal.model.RecruiterProfile;
import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.repository.JobSeekerProfileRepo;
import com.rushi.jobportal.repository.RecruiterProfileRepo;
import com.rushi.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.RequestingUserName;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JobSeekerProfileRepo jobSeekerProfileRepo;

    @Autowired
    private RecruiterProfileRepo recruiterProfileRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UsersRepository usersRepository, JobSeekerProfileRepo jobSeekerProfileRepo,
                       RecruiterProfileRepo recruiterProfileRepo) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
        this.recruiterProfileRepo = recruiterProfileRepo;
    }

    public void addNew(Users users){
//        System.out.println("Users "+users);
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if(userTypeId ==1){
            recruiterProfileRepo.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepo.save(new JobSeekerProfile(savedUser));
        }
    }

    public Optional<Users> checkIfUserExists(Users users){

        return usersRepository.findByEmail(users.getEmail());
    }


    public Object getCurrentUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String userName = authentication.getName();
            Users users = usersRepository.findByEmail(userName).orElseThrow(()->
                    new UsernameNotFoundException("Could not found "+userName));
            int userId = users.getUserId();
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                RecruiterProfile recruiterProfile = recruiterProfileRepo.findById(userId).
                        orElse(new RecruiterProfile(users));
                return recruiterProfile;
            }else{
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepo.findById(userId).
                        orElse(new JobSeekerProfile(users));
                return jobSeekerProfile;
            }
        }
        return null;
    }
}

package com.rushi.jobportal.service;

import com.rushi.jobportal.model.JobSeekerProfile;
import com.rushi.jobportal.model.RecruiterProfile;
import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.repository.JobSeekerProfileRepo;
import com.rushi.jobportal.repository.RecruiterProfileRepo;
import com.rushi.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addNew(Users users){
//        System.out.println("Users "+users);
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
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


}

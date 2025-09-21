package com.rushi.jobportal.service;

import com.rushi.jobportal.model.RecruiterProfile;
import com.rushi.jobportal.repository.RecruiterProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    @Autowired
    private RecruiterProfileRepo recruiterProfileRepo;

    public Optional<RecruiterProfile> getRecruiterProfile(Integer id){
        return recruiterProfileRepo.findById(id);
    }


    public RecruiterProfile addUser(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepo.save(recruiterProfile);
    }
}

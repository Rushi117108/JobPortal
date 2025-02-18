package com.rushi.jobportal.service;

import com.rushi.jobportal.model.UserType;
import com.rushi.jobportal.repository.UsersTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepo usersTypeRepo;

    public UsersTypeService(UsersTypeRepo usersTypeRepo) {
        this.usersTypeRepo = usersTypeRepo;
    }

    public List<UserType> getAll(){
        return usersTypeRepo.findAll();
    }

}

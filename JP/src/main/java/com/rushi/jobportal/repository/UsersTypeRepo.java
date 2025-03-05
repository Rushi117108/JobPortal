package com.rushi.jobportal.repository;

import com.rushi.jobportal.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTypeRepo extends JpaRepository<UserType, Integer> {
}

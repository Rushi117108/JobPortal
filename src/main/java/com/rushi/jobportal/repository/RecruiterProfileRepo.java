package com.rushi.jobportal.repository;

import com.rushi.jobportal.model.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterProfileRepo extends JpaRepository<RecruiterProfile, Integer> {
}

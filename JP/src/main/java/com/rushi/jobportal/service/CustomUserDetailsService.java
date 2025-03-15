package com.rushi.jobportal.service;

import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.repository.UsersRepository;
import com.rushi.jobportal.util.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user =usersRepository.findByEmail(username).orElseThrow(()->new
                UsernameNotFoundException("Could not found user"));
        return new CustomUserDetails(user);
    }
}

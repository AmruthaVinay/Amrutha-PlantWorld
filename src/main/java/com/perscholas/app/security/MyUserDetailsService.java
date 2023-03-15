


package com.perscholas.app.security;


import com.perscholas.app.repository.AuthGroupRepoI;
import com.perscholas.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {


    UserRepository userRepository;
    AuthGroupRepoI authGroupRepoI;
    @Autowired
    public MyUserDetailsService(UserRepository userRepository, AuthGroupRepoI authGroupRepoI) {
        this.userRepository = userRepository;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserPrincipal
                (userRepository.findByEmailAllIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Did not find the email" + username))
                        , authGroupRepoI.findByEmail(username));
    }
}





















/*
import com.perscholas.app.repository.AuthGroupRepository;
import com.perscholas.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    AuthGroupRepository authGroupRepository;
    @Autowired
    public MyUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new AppUserPrincipal(
                userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"))
                , authGroupRepository.findByEmail(username));
    }
}

*/
















/*
import com.perscholas.app.repository.AuthGroupRepository;
import com.perscholas.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailService implements UserDetailsService {
UserRepository userRepository;
AuthGroupRepository authGroupRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserPrincipal(
                userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"))
                , authGroupRepository.findByEmail(username));
    }
}
*/

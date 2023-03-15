package com.perscholas.app.service;
import com.perscholas.app.dto.UserDTO;
import com.perscholas.app.model.AuthGroup;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.AuthGroupRepoI;
import com.perscholas.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthGroupRepoI authGroupRepoI;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

/*      public UserRegistration getUserByEmail(String email, String loginPassword){

          UserRegistration user = userRepository.findById(email).get();
          if(user != null){
              if(user.getEmail().equals(email) && user.getPassword().equals(loginPassword)){
                  return user;
              }
              else{
                  return null;

              }
          }
        else{
            return null;
          }
    }*/

    public UserRegistration getUserByEmail(String email, String loginPassword){

        UserRegistration user = userRepository.findById(email).get();
        if(user != null){
            if(user.getEmail().equals(email) && user.getPassword().equals(loginPassword)){
                return user;
            }
            else{
                return null;

            }
        }
        else{
            return null;
        }
    }
    public UserRegistration saveUser(UserRegistration userRegister) {
         return userRepository.save(userRegister);

    }

    public List<AuthGroup> findByEmail(String email) {
        return authGroupRepoI.findByEmail(email);

    }

    public UserRegistration findByEmailAllIgnoreCase(String email) {
        return userRepository.findById(email).get();

    }

    public UserDTO findUserById(String loginEmail) {
     UserDTO loggedInUser =  userRepository.findById(loginEmail).map((user) -> new UserDTO(user.getEmail(), user.getUserName())).get();

    return loggedInUser;
    }

    public UserRegistration findById(String email) {

        return userRepository.findById(email).get();
    }

    public List<UserRegistration> findAllUsers() {
        return userRepository.findAll();
    }
}

package com.Exam.Service.Implementaion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Exam.Repository.RoleRepository;
import com.Exam.Repository.UserRepository;
import com.Exam.Service.UserService;
import com.Exam.entity.User;
import com.Exam.entity.UserRole;
import com.Exam.helper.UserFoundException;
import com.Exam.helper.UserNotFoundException;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {


        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);

        }

        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) throws UserNotFoundException{
    	
        User user=this.userRepository.findByUsername(username);
        if(user==null) {
        	throw new UserNotFoundException("User not found with username: " + username);   
        }
        return user;
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
    	 if (!userRepository.existsById(userId)) {
             throw new UserNotFoundException("User not found with id: " + userId);
         }
        this.userRepository.deleteById(userId);
    }


}


package com.Exam.Service;

import java.util.Set;

import com.Exam.entity.User;
import com.Exam.entity.UserRole;
import com.Exam.helper.UserNotFoundException;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username) throws UserNotFoundException;

    //delete user by id
    public void deleteUser(Long userId) throws UserNotFoundException;
}

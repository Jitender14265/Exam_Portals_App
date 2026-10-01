package com.Exam.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}

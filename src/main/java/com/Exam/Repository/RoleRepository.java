package com.Exam.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}


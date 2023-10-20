package com.eurodyn.simplewebapp.repository;

import com.eurodyn.simplewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

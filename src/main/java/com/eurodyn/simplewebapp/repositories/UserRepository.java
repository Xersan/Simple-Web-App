package com.eurodyn.simplewebapp.repositories;

import com.eurodyn.simplewebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

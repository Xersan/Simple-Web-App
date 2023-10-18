package com.eurodyn.simplewebapp.repositories;

import com.eurodyn.simplewebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

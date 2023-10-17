package com.eurodyn.simplewebapp.repositories;

import com.eurodyn.simplewebapp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

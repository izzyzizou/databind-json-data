package com.example.jsonapp.repository;

import com.example.jsonapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}

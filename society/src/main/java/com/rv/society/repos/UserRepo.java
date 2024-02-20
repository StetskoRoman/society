package com.rv.society.repos;

import com.rv.society.domain.User;
import org.springframework.data.repository.CrudRepository;
//поменял JPA на круд

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);



}

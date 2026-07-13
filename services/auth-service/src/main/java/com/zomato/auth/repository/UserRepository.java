package com.zomato.auth.repository;

import com.zomato.auth.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailId(String emailId);


    Optional<User> findByPhoneNumber(String phone);

    Optional<User> findByUsernameAndPassword(String username, @Nullable String password);

    Optional<User> findByEmailIdAndPassword(String username, String encodedPassword);

    Optional<User> findByPhoneNumberAndPassword(String username, String encodedPassword);
}

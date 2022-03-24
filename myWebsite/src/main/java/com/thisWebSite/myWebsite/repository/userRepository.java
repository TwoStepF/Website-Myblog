package com.thisWebSite.myWebsite.repository;

import com.thisWebSite.myWebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}

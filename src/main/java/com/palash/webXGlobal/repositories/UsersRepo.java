package com.palash.webXGlobal.repositories;

import com.palash.webXGlobal.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u where lower(u.username) = lower(u.username) ")
    Users findByUsername(String username);
}

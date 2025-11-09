package com.polliceor.cricket.aca.sportsmanagement.repo;

import com.polliceor.cricket.aca.sportsmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<User> findByFirebaseUid(String firebaseUid); // <-- add this

}

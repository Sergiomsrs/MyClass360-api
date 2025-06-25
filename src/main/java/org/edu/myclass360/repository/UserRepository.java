package org.edu.myclass360.repository;

import org.edu.myclass360.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDni(String dni);
}

package com.exemple.api.app.repository;

import java.util.Optional;

import com.exemple.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}

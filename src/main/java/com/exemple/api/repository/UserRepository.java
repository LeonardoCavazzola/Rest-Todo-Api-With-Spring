package com.exemple.api.repository;

import java.util.Optional;

import com.exemple.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

}

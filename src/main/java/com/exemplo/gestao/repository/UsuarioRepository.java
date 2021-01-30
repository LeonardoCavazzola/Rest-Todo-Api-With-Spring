package com.exemplo.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.gestao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}

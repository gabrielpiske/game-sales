package com.venda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venda.venda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByEmail(String email);
}

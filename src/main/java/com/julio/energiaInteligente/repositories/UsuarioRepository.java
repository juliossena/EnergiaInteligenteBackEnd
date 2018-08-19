package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}

package com.julio.energiaInteligente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.julio.energiaInteligente.domain.Circuito;


@Repository
public interface CircuitoRepository extends JpaRepository<Circuito, Integer> {
	
	/*@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Circuito obj INNER JOIN Circuito_Usuario ciruser WHERE ciruser LIKE :email")
	List<Circuito> findForEmailAndId(@Param("email") String email);*/
	List<Circuito> findByUsuario_id(Integer usuario_id);

}

package com.julio.energiaInteligente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Medicao;


@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Integer> {
	
//	@Transactional(readOnly = true)
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Medicao> search(@Param("nome") String nome, Pageable pageRequest);
	List<Medicao> findFirst5ByCircuito_idOrderByHorarioDesc(Integer id);

}

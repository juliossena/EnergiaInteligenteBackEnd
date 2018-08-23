package com.julio.energiaInteligente.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.julio.energiaInteligente.domain.Medicao;


@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Integer> {
	
//	@Transactional(readOnly = true)
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Medicao> search(@Param("nome") String nome, Pageable pageRequest);
	List<Medicao> findFirst1ByCircuito_idOrderByHorarioDesc(Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT MAX(obj.potencia) from Medicao obj WHERE obj.circuito.id LIKE :idCircuito AND obj.horario between :inicio AND :termino")
	float findPico(@Param("idCircuito") Integer idCircuito, @Param("inicio") Date inicio, @Param("termino") Date termino) throws Exception;
	
	@Transactional(readOnly = true)
	@Query("SELECT AVG(obj.potencia) from Medicao obj WHERE obj.circuito.id LIKE :idCircuito AND obj.horario between :inicio AND :termino")
	float findAVG(@Param("idCircuito") Integer idCircuito, @Param("inicio") Date inicio, @Param("termino") Date termino) throws Exception;
	
	@Transactional(readOnly = true)
	@Query("SELECT obj from Medicao obj WHERE obj.circuito.id LIKE :idCircuito AND obj.horario between :inicio AND :termino ORDER BY obj.potencia DESC")
	List<Medicao> findObjetoPico(@Param("idCircuito") Integer idCircuito, @Param("inicio") Date inicio, @Param("termino") Date termino) throws Exception;
	
	@Transactional(readOnly = true)
	@Query("SELECT SUM(obj.consumido) from Medicao obj WHERE obj.circuito.id LIKE :idCircuito AND obj.horario between :inicio AND :termino")
	float findConsumoTotal(@Param("idCircuito") Integer idCircuito, @Param("inicio") Date inicio, @Param("termino") Date termino) throws Exception;

}

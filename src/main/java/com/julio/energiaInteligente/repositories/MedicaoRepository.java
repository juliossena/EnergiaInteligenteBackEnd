package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Medicao;


@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Integer> {

}

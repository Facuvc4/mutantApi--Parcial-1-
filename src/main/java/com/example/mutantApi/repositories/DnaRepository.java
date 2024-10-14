package com.example.mutantApi.repositories;

import com.example.mutantApi.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<Dna,Long> {
    Optional<Dna> findByDnaSequence(String[] dnaSequence);
    long countByIsMutant(boolean isMutant);
}

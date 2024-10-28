package com.example.mutantApi.services;

import com.example.mutantApi.dtos.StatsResponseDto;
import com.example.mutantApi.repositories.DnaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final DnaRepository dnaRepository;

    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public StatsResponseDto getStats (){
        long mutant = dnaRepository.countByIsMutant(true);
        long human = dnaRepository.countByIsMutant(false);
        float ratio = (float) mutant / human;
        return StatsResponseDto.builder()
                .count_mutant_dna(mutant)
                .count_human_dna(human)
                .ratio(ratio)
                .build();
    }
}
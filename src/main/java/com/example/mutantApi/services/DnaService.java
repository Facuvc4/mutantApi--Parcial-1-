package com.example.mutantApi.services;

import com.example.mutantApi.dtos.StatsResponseDto;
import com.example.mutantApi.entities.Dna;
import com.example.mutantApi.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.mutantApi.helpers.DnaHelper.checkSequence;
import static com.example.mutantApi.helpers.DnaHelper.sequenceLength;

@Service
public class DnaService {

    @Autowired
    DnaRepository dnaRepository;

    public boolean isMutant(String[] dna) {

        Optional<Dna> existingDna = dnaRepository.findByDnaSequence(dna);

        if (existingDna.isPresent()) {
            return existingDna.get().getIsMutant();
        }


        int n = dna.length;
        int mutantSequences = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j <= n - sequenceLength && checkSequence(dna, i, j, 0, 1)) {
                    mutantSequences++;
                }

                if (i <= n - sequenceLength && checkSequence(dna, i, j, 1, 0)) {
                    mutantSequences++;
                }

                if (i <= n - sequenceLength && j <= n - sequenceLength && checkSequence(dna, i, j, 1, 1)) {
                    mutantSequences++;
                }

                if (i <= n - sequenceLength && j >= sequenceLength - 1 && checkSequence(dna, i, j, 1, -1)) {
                    mutantSequences++;
                }


                if (mutantSequences > 1) {
                    Dna newDnaSequence = Dna.builder().dnaSequence(dna).isMutant(true).build();
                    dnaRepository.save(newDnaSequence);
                    return true;
                }
            }
        }

        Dna newDnaSequence = Dna.builder().dnaSequence(dna).isMutant(false).build();
        dnaRepository.save(newDnaSequence);
        return false;
    }

    public StatsResponseDto getStats() {
        long countMutant = dnaRepository.countByIsMutant(true);
        long countHuman = dnaRepository.countByIsMutant(false);
        double ratio = (countHuman == 0) ? 0 : (double) countMutant / countHuman;
        return StatsResponseDto.builder().count_human_dna(countHuman).count_mutant_dna(countMutant).ratio(ratio).build();
    }
}

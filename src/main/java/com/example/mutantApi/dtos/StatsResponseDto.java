package com.example.mutantApi.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponseDto {
    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;
}

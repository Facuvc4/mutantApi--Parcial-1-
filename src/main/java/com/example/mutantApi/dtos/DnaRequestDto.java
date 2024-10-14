package com.example.mutantApi.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequestDto {
    private String[] dna;
}

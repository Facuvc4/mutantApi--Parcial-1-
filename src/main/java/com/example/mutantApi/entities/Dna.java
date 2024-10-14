package com.example.mutantApi.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dna_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_sequence")
    private String[] dnaSequence;

    @Column(name = "is_mutant")
    private Boolean isMutant;
}

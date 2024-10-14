package com.example.mutantApi.controllers;

import com.example.mutantApi.dtos.DnaRequestDto;
import com.example.mutantApi.dtos.StatsResponseDto;
import com.example.mutantApi.services.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.mutantApi.helpers.DnaHelper.dnaValidator;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class DnaController {
    @Autowired
    protected DnaService service;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequestDto dnaRequest) {
        try {
            if(!dnaValidator(dnaRequest.getDna())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400 Bad Request");
            }

            boolean isMutant = service.isMutant(dnaRequest.getDna());

            if (isMutant) {
                return ResponseEntity.ok("Mutant detected");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not a mutant");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponseDto> statsResponse() {
        try {
            StatsResponseDto stats = service.getStats();
            return ResponseEntity.ok(stats);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatsResponseDto());
        }
    }
}

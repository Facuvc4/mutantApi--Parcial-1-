package com.example.mutantApi.controllers;

import com.example.mutantApi.dtos.DnaRequestDto;
import com.example.mutantApi.dtos.StatsResponseDto;
import com.example.mutantApi.services.DnaService;
import com.example.mutantApi.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class DnaController {
    @Autowired
    protected DnaService dnaService;

    @Autowired
    protected StatsService statsService;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequestDto dnaRequest) {
        try {
            if(!dnaService.analyzeDna(dnaRequest.getDna())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400 Bad Request");
            }

            boolean isMutant = dnaService.isMutant(dnaRequest.getDna());

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
            StatsResponseDto stats = statsService.getStats();
            return ResponseEntity.ok(stats);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatsResponseDto());
        }
    }
}

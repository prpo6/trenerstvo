package com.golfapp.trenerstvo.controller;

import com.golfapp.trenerstvo.dto.TerminCreateDto;
import com.golfapp.trenerstvo.dto.TerminDto;
import com.golfapp.trenerstvo.dto.TerminUpdateDto;
import com.golfapp.trenerstvo.service.TerminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/termini")
public class TerminController {

    private final TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @GetMapping
    public List<TerminDto> getAll() {
        return terminService.getAll();
    }

    @GetMapping("/{id}")
    public TerminDto getById(@PathVariable UUID id) {
        return terminService.getById(id);
    }

    @GetMapping("/trener/{trenerId}")
    public List<TerminDto> getByTrener(@PathVariable UUID trenerId) {
        return terminService.getByTrener(trenerId);
    }

    @GetMapping("/clan/{clanId}")
    public List<TerminDto> getByClan(@PathVariable UUID clanId) {
        return terminService.getByClan(clanId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TerminDto create(@RequestBody TerminCreateDto dto) {
        return terminService.create(dto);
    }

    @PutMapping("/{id}")
    public TerminDto update(@PathVariable UUID id, @RequestBody TerminUpdateDto dto) {
        return terminService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        terminService.delete(id);
    }
}

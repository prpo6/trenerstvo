package com.golfapp.trenerstvo.controller;

import com.golfapp.trenerstvo.model.Trener;
import com.golfapp.trenerstvo.service.TrenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trenerji")
@CrossOrigin(origins = "*")
public class TrenerController {

    private final TrenerService trenerService;

    public TrenerController(TrenerService trenerService) {
        this.trenerService = trenerService;
    }

    @GetMapping
    public List<Trener> getAll() {
        return trenerService.getAll();
    }

    @GetMapping("/{id}")
    public Trener getById(@PathVariable UUID id) {
        return trenerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trener create(@RequestBody Trener trener) {
        return trenerService.create(trener);
    }

    @PutMapping("/{id}")
    public Trener update(@PathVariable UUID id, @RequestBody Trener trener) {
        return trenerService.update(id, trener);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        trenerService.delete(id);
    }
}

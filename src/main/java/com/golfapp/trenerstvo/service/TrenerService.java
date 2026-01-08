package com.golfapp.trenerstvo.service;

import com.golfapp.trenerstvo.exception.ResourceNotFoundException;
import com.golfapp.trenerstvo.model.Trener;
import com.golfapp.trenerstvo.repository.TrenerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrenerService {

    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    public List<Trener> getAll() {
        return trenerRepository.findAll();
    }

    public Trener getById(UUID id) {
        return trenerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trener not found with ID: " + id));
    }

    public Trener create(Trener trener) {
        if (trener == null) {
            throw new IllegalArgumentException("Trener is null");
        }
        if (isBlank(trener.getIme()) || isBlank(trener.getPriimek())) {
            throw new IllegalArgumentException("Ime, priimek missing");
        }

        trener.setId(null);
        return trenerRepository.save(trener);
    }

    public Trener update(UUID id, Trener trener) {
        if (trener == null) {
            throw new IllegalArgumentException("Trener is null");
        }

        Trener existing = trenerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trener not found with ID: " + id));

        if (!isBlank(trener.getIme())) existing.setIme(trener.getIme());
        if (!isBlank(trener.getPriimek())) existing.setPriimek(trener.getPriimek());

        existing.setEmail(trener.getEmail());
        existing.setTelefon(trener.getTelefon());
        existing.setLicenca(trener.getLicenca());

        return trenerRepository.save(existing);
    }

    public void delete(UUID id) {
        if (!trenerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trener not found with ID: " + id);
        }
        trenerRepository.deleteById(id);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}

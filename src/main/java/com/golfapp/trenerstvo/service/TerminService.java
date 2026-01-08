package com.golfapp.trenerstvo.service;

import com.golfapp.trenerstvo.dto.TerminCreateDto;
import com.golfapp.trenerstvo.dto.TerminDto;
import com.golfapp.trenerstvo.dto.TerminUpdateDto;
import com.golfapp.trenerstvo.exception.ConflictException;
import com.golfapp.trenerstvo.exception.ResourceNotFoundException;
import com.golfapp.trenerstvo.model.Termin;
import com.golfapp.trenerstvo.model.Trener;
import com.golfapp.trenerstvo.repository.TerminRepository;
import com.golfapp.trenerstvo.repository.TrenerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TerminService {

    private final TerminRepository terminRepository;
    private final TrenerRepository trenerRepository;

    public TerminService(TerminRepository terminRepository, TrenerRepository trenerRepository) {
        this.terminRepository = terminRepository;
        this.trenerRepository = trenerRepository;
    }

    @Transactional(readOnly = true)
    public List<TerminDto> getAll() {
        return terminRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TerminDto getById(UUID id) {
        Termin termin = terminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termin not found with ID: " + id));
        return toDto(termin);
    }

    @Transactional(readOnly = true)
    public List<TerminDto> getByTrener(UUID trenerId) {
        return terminRepository.findByTrener_Id(trenerId).stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<TerminDto> getByClan(UUID clanId) {
        return terminRepository.findByClanId(clanId).stream().map(this::toDto).toList();
    }

    @Transactional
    public TerminDto create(TerminCreateDto dto) {
        validateCreate(dto);

        Trener trener = trenerRepository.findById(dto.getTrenerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trener not found with ID: " + dto.getTrenerId()));

        boolean overlap = terminRepository.existsByTrener_IdAndZacetekLessThanAndKonecGreaterThan(
                trener.getId(), dto.getKonec(), dto.getZacetek()
        );
        if (overlap) {
            throw new ConflictException("Trener ima ze termin v tem casovnem intervalu");
        }

        Termin termin = new Termin();
        termin.setTrener(trener);
        termin.setClanId(dto.getClanId());
        termin.setZacetek(dto.getZacetek());
        termin.setKonec(dto.getKonec());
        termin.setOpombe(dto.getOpombe());

        Termin saved = terminRepository.save(termin);
        return toDto(saved);
    }

    @Transactional
    public TerminDto update(UUID id, TerminUpdateDto dto) {
        validateUpdate(dto);

        Termin existing = terminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termin not found with ID: " + id));

        Trener trener = trenerRepository.findById(dto.getTrenerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trener not found with ID: " + dto.getTrenerId()));

        var overlaps = terminRepository.findByTrener_IdAndZacetekLessThanAndKonecGreaterThan(
                trener.getId(), dto.getKonec(), dto.getZacetek()
        );
        boolean hasOtherOverlap = overlaps.stream().anyMatch(t -> !t.getId().equals(existing.getId()));
        if (hasOtherOverlap) {
            throw new ConflictException("Trener ima ze termin v tem casovnem intervalu");
        }

        existing.setTrener(trener);
        existing.setClanId(dto.getClanId());
        existing.setZacetek(dto.getZacetek());
        existing.setKonec(dto.getKonec());
        existing.setOpombe(dto.getOpombe());

        return toDto(terminRepository.save(existing));
    }

    @Transactional
    public void delete(UUID id) {
        if (!terminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Termin not found with ID: " + id);
        }
        terminRepository.deleteById(id);
    }

    private void validateCreate(TerminCreateDto dto) {
        if (dto == null) throw new IllegalArgumentException("Termin is null");
        if (dto.getTrenerId() == null) throw new IllegalArgumentException("No trenerId");
        if (dto.getClanId() == null) throw new IllegalArgumentException("No clanId");
        if (dto.getZacetek() == null || dto.getKonec() == null) throw new IllegalArgumentException("Manjka zacetek/konec");
        if (!dto.getZacetek().isBefore(dto.getKonec())) throw new IllegalArgumentException("Zacetek mora biti pred koncem");
    }

    private void validateUpdate(TerminUpdateDto dto) {
        if (dto == null) throw new IllegalArgumentException("Termin payload is null");
        if (dto.getTrenerId() == null) throw new IllegalArgumentException("No trenerId");
        if (dto.getClanId() == null) throw new IllegalArgumentException("No clanId");
        if (dto.getZacetek() == null || dto.getKonec() == null) throw new IllegalArgumentException("Manjka zacetek/konec");
        if (!dto.getZacetek().isBefore(dto.getKonec())) throw new IllegalArgumentException("Zacetek mora biti pred koncem");
    }

    private TerminDto toDto(Termin t) {
        return new TerminDto(
                t.getId(),
                t.getTrener().getId(),
                t.getClanId(),
                t.getZacetek(),
                t.getKonec(),
                t.getOpombe()
        );
    }
}

package com.golfapp.trenerstvo.repository;

import com.golfapp.trenerstvo.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TerminRepository extends JpaRepository<Termin, UUID> {

    List<Termin> findByTrener_Id(UUID trenerId);

    List<Termin> findByClanId(UUID clanId);

    // za prekrivanje, ce: existing.zacetek < newKonec AND existing.konec > newZacetek
    List<Termin> findByTrener_IdAndZacetekLessThanAndKonecGreaterThan(
            UUID trenerId,
            LocalDateTime konec,
            LocalDateTime zacetek
    );
}

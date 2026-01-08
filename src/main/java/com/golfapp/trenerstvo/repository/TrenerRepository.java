package com.golfapp.trenerstvo.repository;

import com.golfapp.trenerstvo.model.Trener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrenerRepository extends JpaRepository<Trener, UUID> {
    
}

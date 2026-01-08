package com.golfapp.trenerstvo.service;

import com.golfapp.trenerstvo.model.Trener;
import com.golfapp.trenerstvo.repository.TrenerRepository;

import java.util.List;

public class TrenerService {
    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    public List<Trener> getAll() {
        return trenerRepository.findAll();
    }
}

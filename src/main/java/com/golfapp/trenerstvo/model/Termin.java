package com.golfapp.trenerstvo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "termin")
public class Termin {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trener_id", nullable = false)
    private Trener trener;

    @Column(name = "clan_id", nullable = false)
    private UUID clanId;

    @Column(nullable = false)
    private LocalDateTime zacetek;

    @Column(nullable = false)
    private LocalDateTime konec;

    private String opombe;

    public Termin() {}

    // Getterji in setterji

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public UUID getClanId() {
        return clanId;
    }

    public void setClanId(UUID clanId) {
        this.clanId = clanId;
    }

    public LocalDateTime getZacetek() {
        return zacetek;
    }

    public void setZacetek(LocalDateTime zacetek) {
        this.zacetek = zacetek;
    }

    public LocalDateTime getKonec() {
        return konec;
    }

    public void setKonec(LocalDateTime konec) {
        this.konec = konec;
    }

    public String getOpombe() {
        return opombe;
    }

    public void setOpombe(String opombe) {
        this.opombe = opombe;
    }
}

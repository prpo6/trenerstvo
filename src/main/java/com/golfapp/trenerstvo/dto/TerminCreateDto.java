package com.golfapp.trenerstvo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TerminCreateDto {

    private UUID trenerId;
    private UUID clanId;
    private LocalDateTime zacetek;
    private LocalDateTime konec;
    private String opombe;

    public TerminCreateDto() {}

    public UUID getTrenerId() { 
        return trenerId; 
    }

    public void setTrenerId(UUID trenerId) { 
        this.trenerId = trenerId; 
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

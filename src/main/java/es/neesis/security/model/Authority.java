package es.neesis.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Authority {
    @ManyToOne
    private User user;
    private String authority;
}

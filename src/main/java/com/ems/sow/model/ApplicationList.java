package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_lists")
public class ApplicationList {
    @Id
    @Column(nullable = false, length=40)
    private String applicationId;
    @Column (nullable = false, length=100)
    private String applicationName;
    @Column (nullable = false, length=255)
    private String description;
    @Column (nullable = false, length=5)
    private Boolean status;

    @PrePersist
    public void prePersist() {
        status = true;
    }
}

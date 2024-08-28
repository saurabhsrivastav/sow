package com.ems.sow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_lists")
public class ApplicationList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer applicationId;
    @NonNull
    @Column(length=255, columnDefinition = "application name default data type String")
    private String applicationName;
    @NonNull
    @Column(length=255, columnDefinition = "description default data type String")
    private String description;
    @Column(columnDefinition = "BOOLEAN default false")
    private Boolean status;

    @PrePersist
    public void prePersist() {
        status = false;
    }

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name="fk_application_id",referencedColumnName = "application_id")
    //private List<CustomerList> customerList;

}

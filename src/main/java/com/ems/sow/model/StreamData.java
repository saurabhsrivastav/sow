package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stream_data")
public class StreamData {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private String id;
    @Column(nullable = false, length=50)
    private String objId;
    @Column(nullable = false, length=50)
    private String osd;
    @Column(nullable = false, length=50)
    private String mdbid;
    @Column(nullable = false)
    private String eventTimestamp;
    @Column(nullable = false)
    private Integer recType;
    @Column(nullable = false, length=50)
    private String packetAuthId;
    @Column(nullable = false, length=50)
    private String model;
    @Column(columnDefinition = "JSONB")
    private String teleData;

}

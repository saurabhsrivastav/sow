package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date eventTimestamp;
    @Column(nullable = false)
    private Integer recType;
    @Column(nullable = false, length=50)
    private String packetAuthId;
    @Column(nullable = false, length=50)
    private String model;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String p6;
    private String p7;
    private String p8;
    private String p9;
    private String p10;
    private String p11;
    private String p12;
    private String p13;
    private String p14;
    private String p15;
    private String p16;
    private String p17;
    private String p18;
    private String p19;
    private String p20;
    private String p21;
    private String p22;
    private String p23;
    private String p24;
    private String p25;
    private String p26;
    private String p27;
    private String p28;
    private String p29;
    private String p30;
    private String p31;
    private String p32;
}

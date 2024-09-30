package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_parameter")
@Entity
public class DeviceParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_seq")
    @SequenceGenerator(name = "parameter_seq", sequenceName = "parameter_seq", allocationSize = 1)  // Use IDENTITY or AUTO for automatic generation
    private BigInteger parameterId;
    @Column(nullable = false, length=40)
    private String parameterName;
    @Column(nullable = false, length=100)
    private String parameterDescription;
    @Column(nullable = false, length=40)
    private String unit;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minimum;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal  maximum;

}


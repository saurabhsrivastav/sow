package com.ems.sow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images_stock")
public class ImagesStock {

    @Id
    private String imageId;

    @Column(name="image_timestamp", nullable = false)
    private String imageTimeStamp;
    @Column(name="osd", nullable = false)
    private String osd;
    @Column(name="modbus_id", nullable = false)
    private String modbusId;
    @Column(name="full_image_name", nullable = false)
    private String fullImageName;
    private String fileUrl;
}
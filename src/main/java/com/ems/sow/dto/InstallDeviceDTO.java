package com.ems.sow.dto;

import lombok.Data;

import java.util.List;

@Data
public class InstallDeviceDTO {

        private String deviceId;
        private String customerId;
        private String deviceModbus;
        private String deviceName;
        private String deviceStatus;
        private String deviceType;
        private String modelNumber;
        private String rtuCategory;
        private String rtuId;
        private String rtuName;
        private String serialNumber;
        private List<InstallDeviceParameterDTO> deviceParameterInfo; // Match frontend JSON key

        @Data
        public static class InstallDeviceParameterDTO {
            private int delay;
            private String deviceModbus;
            private String parameterCode;
            private String parameterName;
            private String serialNumber;
            private int thresholdMax;
            private int thresholdMin;
            private String unit;
        }
}

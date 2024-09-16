package com.ems.sow.payload;

import lombok.Builder;

@Builder
public class Files {
    private String name;
    private String type;
    private long contentLength;
    private byte[] imageData;
}

package com.ems.sow.projection;

public interface StreamDataProjection {
    String getParameterCode();
    String getParameterName();
    String getObjId();
    String getOsd();
    String getModel();
    String getEventTimestamp();
    String getUpdatedTeleData();
}

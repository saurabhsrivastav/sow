package com.ems.sow.projection;

import java.sql.Timestamp;

public interface StreamDataProjection {
    String getParameterCode();
    String getParameterName();
    String getValue();
    String getObjId();
    String getOsd();
    String getModel();
    String getEventTimestamp();
    String getUpdatedTeleData();
}

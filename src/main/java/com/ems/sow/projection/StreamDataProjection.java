package com.ems.sow.projection;

public interface StreamDataProjection {

    String getObjId();           // Maps to sd.obj_id
    String getOsd();             // Maps to sd.osd
    String getEventTimestamp();  // Maps to sd.event_timestamp
    String getMdbid();           // Maps to sd.mdbid
    String getModel();           // Maps to sd.model
    String getUpdatedTeleData(); // Maps to jsonb_object_agg result as TEXT
}

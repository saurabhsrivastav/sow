package com.ems.sow.projection;

public interface IApplicationListProj {

    Integer getApplicationID();
    String getApplicationName();
    String getApplicationDescription();
    Boolean getStatus();
    Integer getActiveCustomerCount();

}

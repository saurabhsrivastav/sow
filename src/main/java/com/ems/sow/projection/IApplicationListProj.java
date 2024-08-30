package com.ems.sow.projection;

public interface IApplicationListProj {

    Integer getApplicationID();
    String getApplicationName();
    String getDescription();
    Boolean getStatus();
    Integer getActiveCustomerCount();

}

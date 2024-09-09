package com.ems.sow.projection;

public interface IApplicationListProj {

    String getApplicationId();
    String getApplicationName();
    String getDescription();
    Boolean getStatus();
    Integer getActiveCustomerCount();

}

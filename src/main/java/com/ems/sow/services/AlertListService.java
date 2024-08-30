package com.ems.sow.services;

import com.ems.sow.model.AlertList;

import java.util.List;

public interface AlertListService {

    /**
     * Get All Alert List
     *
     * @return
     */

    List<AlertList> getAlertByCustomerId(Integer id);

}

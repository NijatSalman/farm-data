package com.company.services.impl;

import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import com.company.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmDynamicSelectFilterService {

    private final FarmService farmService;

    public TableView<FarmDto> selectFarms(int offset, int pageSize, List<Integer> months, String sensorType) {
        if (months == null && sensorType == null) {
            return farmService.selectAllFarms(offset, pageSize);
        } else if (months == null) {
            return farmService.selectAllFarmsBySensorType(offset, pageSize, sensorType);
        } else if (sensorType == null) {
            return farmService.selectAllFarmsByMonth(offset, pageSize, months);
        }
        return farmService.selectAllFarmsByMonthsAndSensorType(offset, pageSize, months, sensorType);
    }

}

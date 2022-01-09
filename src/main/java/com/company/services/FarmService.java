package com.company.services;
import com.company.dao.entity.Farm;
import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FarmService {
     void saveAll(List<Farm> farms);

     TableView<FarmDto> selectAllFarmsByMonth(int offset, int pageSize, List<Integer> months);

     TableView<FarmDto> selectAllFarmsByMonthsAndSensorType(int offset, int pageSize,List<Integer> months,String sensorType);

     TableView<FarmDto> selectAllFarmsBySensorType(int offset, int pageSize,String sensorType);

     TableView<FarmDto> selectAllFarms(int offset,int pageSize);


}

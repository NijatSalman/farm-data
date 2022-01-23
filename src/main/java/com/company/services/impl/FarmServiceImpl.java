package com.company.services.impl;

import com.company.dao.entity.Farm;
import com.company.dao.repository.FarmRepository;
import com.company.mapper.FarmMapper;
import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import com.company.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("farmService")
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    @Autowired
    FarmRepository farmRepository;

    @Override
    public void saveAll(List<Farm> farms) {
        farmRepository.saveAll(farms);
    }

    @Override
    public TableView<FarmDto> selectAllFarmsByMonth(int offset, int pageSize, List<Integer> months) {
        Page<Farm> farms = farmRepository.findAllByMonths(PageRequest.of(offset, pageSize), months);
        List<FarmDto> farmDtoList = farms.stream().map(FarmMapper.INSTANCE::toDto).collect(Collectors.toList());

        return TableView.<FarmDto>builder()
                .list(farmDtoList)
                .totalElements(farms.getTotalElements())
                .totalPages(farms.getTotalPages())
                .recordCount(farms.getSize())
                .build();
    }

    @Override
    public TableView<FarmDto> selectAllFarms(int offset, int pageSize) {
        Page<Farm> farms = farmRepository.findAll(PageRequest.of(offset, pageSize));

        List<FarmDto> farmDtoList = farms.stream().map(FarmMapper.INSTANCE::toDto).collect(Collectors.toList());

        return TableView.<FarmDto>builder()
                .list(farmDtoList)
                .totalElements(farms.getTotalElements())
                .totalPages(farms.getTotalPages())
                .recordCount(farms.getSize())
                .build();
    }


    @Override
    public TableView<FarmDto> selectAllFarmsByMonthsAndSensorType(int offset, int pageSize, List<Integer> months, String sensorType) {
        Page<Farm> farms = farmRepository.findAllByMonthsAndSensorType(PageRequest.of(offset, pageSize), months, sensorType);

        List<FarmDto> farmDtoList = farms.stream().map(FarmMapper.INSTANCE::toDto).collect(Collectors.toList());

        return TableView.<FarmDto>builder()
                .list(farmDtoList)
                .totalElements(farms.getTotalElements())
                .totalPages(farms.getTotalPages())
                .recordCount(farms.getSize())
                .build();
    }

    @Override
    public TableView<FarmDto> selectAllFarmsBySensorType(int offset, int pageSize, String sensorType) {
        Page<Farm> farms = farmRepository.findAllBySensorType(PageRequest.of(offset, pageSize), sensorType);

        List<FarmDto> farmDtoList = farms.stream().map(FarmMapper.INSTANCE::toDto).collect(Collectors.toList());

        return TableView.<FarmDto>builder()
                .list(farmDtoList)
                .totalElements(farms.getTotalElements())
                .totalPages(farms.getTotalPages())
                .recordCount(farms.getSize())
                .build();
    }

}

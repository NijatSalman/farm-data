package com.company.services;


import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import com.company.services.impl.FarmDynamicSelectFilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FarmDynamicSelectFilterServiceTest {
    private static final TableView<FarmDto> tableView = TableView.<FarmDto>builder().totalPages(55).build();
    List<Integer> months = Arrays.asList(1, 2);

    @Mock
    private FarmService farmService;

    @InjectMocks
    private FarmDynamicSelectFilterService service;

    @Test
    void shouldSelectAllFarmsWhenMonthsAndSensorTypeAreNull() {
        given(farmService.selectAllFarms(0, 10)).willReturn(tableView);
        TableView<FarmDto> selectedFarms = service.selectFarms(0, 10, null, null);

        verify(farmService, atMostOnce()).selectAllFarms(0, 10);
        verify(farmService, never()).selectAllFarmsBySensorType(anyInt(), anyInt(), anyString());
        verify(farmService, never()).selectAllFarmsByMonth(anyInt(), anyInt(), anyList());
        verify(farmService, never()).selectAllFarmsByMonthsAndSensorType(anyInt(), anyInt(), anyList(), anyString());
        assertEquals(tableView, selectedFarms);
    }

    @Test
    void shouldSelectAllFarmsByOnlySensorTypeWhenMonthsIsNull() {
        given(farmService.selectAllFarmsBySensorType(0, 10, "ph")).willReturn(tableView);
        TableView<FarmDto> selectedFarms = service.selectFarms(0, 10, null, "ph");

        verify(farmService, atMostOnce()).selectAllFarmsBySensorType(0, 10, "ph");
        verify(farmService, never()).selectAllFarms(anyInt(), anyInt());
        verify(farmService, never()).selectAllFarmsByMonth(anyInt(), anyInt(), anyList());
        verify(farmService, never()).selectAllFarmsByMonthsAndSensorType(anyInt(), anyInt(), anyList(), anyString());
        assertEquals(tableView, selectedFarms);
    }

    @Test
    void shouldSelectAllFarmsByOnlyMonthsWhenSensorTypeIsNull() {
        given(farmService.selectAllFarmsByMonth(0, 10, months)).willReturn(tableView);
        TableView<FarmDto> selectedFarms = service.selectFarms(0, 10, months, null);

        verify(farmService, atMostOnce()).selectAllFarmsByMonth(0, 10, months);
        verify(farmService, never()).selectAllFarmsBySensorType(anyInt(), anyInt(), anyString());
        verify(farmService, never()).selectAllFarms(anyInt(), anyInt());
        verify(farmService, never()).selectAllFarmsByMonthsAndSensorType(anyInt(), anyInt(), anyList(), anyString());
        assertEquals(tableView, selectedFarms);
    }

    @Test
    void shouldSelectAllFarmsByMonthsAndSensorTypeWhenMonthsAndSensorTypeAreProvided() {
        given(farmService.selectAllFarmsByMonthsAndSensorType(0, 10, months, "ph")).willReturn(tableView);
        TableView<FarmDto> selectedFarms = service.selectFarms(0, 10, months, "ph");

        verify(farmService, atMostOnce()).selectAllFarmsByMonthsAndSensorType(0, 10, months, "ph");
        verify(farmService, never()).selectAllFarms(anyInt(), anyInt());
        verify(farmService, never()).selectAllFarmsByMonth(anyInt(), anyInt(), anyList());
        verify(farmService, never()).selectAllFarmsBySensorType(anyInt(), anyInt(), anyString());
        assertEquals(tableView, selectedFarms);
    }

}

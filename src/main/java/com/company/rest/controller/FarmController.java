package com.company.rest.controller;

import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import com.company.services.FarmService;
import com.company.utility.CsvReadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("farm")
public class FarmController {

    @Autowired
    @Qualifier("farmService")
    FarmService farmService;

    @PostMapping(value = "/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        String fileExcelFormat="application/vnd.ms-excel";
        String fileCsvFormat="text/csv";
        if (!(fileExcelFormat.equals(file.getContentType()) || fileCsvFormat.equals(file.getContentType()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File format is invalid");
        }
        farmService.saveAll(CsvReadUtility.filteredDataMapToEntity(FarmDto.class, file.getInputStream()));
    }

        @GetMapping("select/{offset}/{pageSize}")
        public TableView<FarmDto> getFarmsByMonth(@PathVariable int offset,
                                                  @PathVariable int pageSize,
                                                  @RequestParam(required = false) List<Integer> months,
                                                  @RequestParam(required = false) String sensorType){
        if (months==null && sensorType==null){
            return farmService.selectAllFarms(offset, pageSize);
        }else if (months==null){
            return  farmService.selectAllFarmsBySensorType(offset,pageSize,sensorType);
        }else if (sensorType==null){
            return farmService.selectAllFarmsByMonth(offset, pageSize, months);
        }
            return farmService.selectAllFarmsByMonthsAndSensorType(offset,pageSize,months,sensorType);
    }

    @GetMapping("select/")
    public TableView<FarmDto> getInitialFarms(){
            return farmService.selectAllFarms(0, 10);
    }

}

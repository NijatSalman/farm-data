package com.company.rest.controller;

import com.company.model.dto.FarmDto;
import com.company.model.view.TableView;
import com.company.services.FarmService;
import com.company.services.impl.FarmDynamicSelectFilterService;
import com.company.utility.CsvReadUtility;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    FarmService farmService;

    @Autowired
    FarmDynamicSelectFilterService farmDynamicSelectFilterService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value="upload only csv file",notes="you cannot dupload except csv file")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        String fileExcelFormat = "application/vnd.ms-excel";
        String fileCsvFormat = "text/csv";
        if (!(fileExcelFormat.equals(file.getContentType()) || fileCsvFormat.equals(file.getContentType()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File format is invalid");
        }
        farmService.saveAll(CsvReadUtility.filteredDataMapToEntity(FarmDto.class, file.getInputStream()));
    }

    @GetMapping("select/{offset}/{pageSize}")
    @ApiOperation(value="get data up to an equal amount of pagesize",notes="if there are values it will get equal amount of pagesize with considering offset value",response=TableView.class)
    public TableView<FarmDto> getFarmsByMonth(@PathVariable int offset,
                                              @PathVariable int pageSize,
                                              @RequestParam(required = false) List<Integer> months,
                                              @RequestParam(required = false) String sensorType) {

        return farmDynamicSelectFilterService.selectFarms(offset, pageSize, months, sensorType);
    }

    @GetMapping("select/")
    @ApiOperation(value="get first 10 farm datas",notes="if there are values it will get first 10 values",response=TableView.class)
    public TableView<FarmDto> getInitialFarms() {
        return farmService.selectAllFarms(0, 10);
    }

}

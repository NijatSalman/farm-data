package com.company.rest.controller;

import com.company.dao.entity.Farm;
import com.company.services.FarmService;
import com.company.utility.CsvReadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("farm")
public class FarmController {

    @Autowired
    @Qualifier("farmService")
    FarmService farmService;

    @PostMapping(value = "/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        farmService.saveAll(CsvReadUtility.read(Farm.class, file.getInputStream()));
    }
}

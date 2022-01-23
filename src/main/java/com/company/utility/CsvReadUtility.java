package com.company.utility;

import com.company.dao.entity.Farm;
import com.company.mapper.FarmMapper;
import com.company.model.dto.FarmDto;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReadUtility {

    private static final CsvMapper mapper = new CsvMapper();

    private static <T> List<T> readDataMapToDtoObject(Class<T> clazz, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

    public static List<Farm> filteredDataMapToEntity(Class<FarmDto> clazz, InputStream stream) throws IOException {
        List<FarmDto> mappingDtoObject = readDataMapToDtoObject(clazz, stream);

        return mappingDtoObject
                .stream()
                .filter(x -> (x.getValue() >= 0 && x.getValue() <= 14 && x.getSensorType().equals("pH")) ||
                        (x.getValue() >= 0 && x.getValue() <= 500 && x.getSensorType().equals("rainFall")) ||
                        (x.getValue() >= -50 && x.getValue() <= 100 && x.getSensorType().equals("temperature")))
                .map(FarmMapper.INSTANCE::toEntity)
                .collect(Collectors.toList());
    }
}

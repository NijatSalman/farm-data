package com.company.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data   
public class FarmDto {

    private Long id;

    @JsonProperty("location")
    private String location;

    @JsonProperty("datetime")
    private Date datetime;

    @JsonProperty("sensorType")
    private String sensorType;

    @JsonProperty("value")
    private float value;
}

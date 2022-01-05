package com.company.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "farm")
@Entity(name = "Farm")
@Getter
@Setter
@EqualsAndHashCode
public class Farm {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "LOCATION")
    @JsonProperty("location")
    private String location;
    @Column(name = "DATE_TIME")
    @JsonProperty("datetime")
    private Date datetime;
    @Column(name = "SENSOR_TYPE")
    @JsonProperty("sensorType")
    private String sensorType;
    @Column(name = "VALUE")
    @JsonProperty("value")
    private float value;
}

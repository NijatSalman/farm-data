package com.company.dao.entity;

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

    @SequenceGenerator(name = "entitySeq", sequenceName = "ID_GENERATOR_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entitySeq")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "LOCATION")
    private String location;
    @Column(name = "DATE_TIME")
    private Date dateTime;
    @Column(name = "SENSOR_TYPE")
    private String sensorType;
    @Column(name = "VALUE")
    private String value;
}

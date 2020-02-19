package com.intercorp.challenge.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ApiModel("Client Kpi model")
public class ClientKpi {
    private Double ageAverage;
    private Double ageStandardDeviation;
}

package com.intercorp.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dozer.Mapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter @ToString
@ApiModel("Client model")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IcClient {
    @NotEmpty(message = "field nombre cannot be null nor empty")
    @ApiModelProperty(value = "name of the client")
    @Mapping("name")
    private String nombre;

    @NotNull(message = "field apellido cannot be null nor empty")
    @ApiModelProperty(value = "last name of the client")
    @Mapping("lastName")
    private String apellido;

    @Min(value = 10, message = "Age should not be less than 10")
    @Max(value = 130, message = "Age should not be greater than 130")
    @NotNull(message = "field edad cannot be null")
    @ApiModelProperty(value = "age of the client")
    @Mapping("age")
    private Integer edad;

    @NotNull(message = "field nacimiento cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
    @ApiModelProperty(value = "birthdate of the client")
    @Mapping("birthDate")
    private LocalDate nacimiento;

    @ApiModelProperty(value = "Date of likely death", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private LocalDate fallecimientoProbable;

    @ApiModelProperty(value = "Id of the client resource", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private transient Long id;
}

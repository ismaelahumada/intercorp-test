package com.intercorp.challenge.api.response;


import com.intercorp.challenge.model.ClientKpi;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("Client Kpi Response")
public class IcClientKpiResponse extends IcApiResponse {
    private ClientKpi clientKpi;

    public IcClientKpiResponse(ClientKpi clientKpi, String message) {
        super(message);
        this.clientKpi = clientKpi;
    }
}

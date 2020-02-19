package com.intercorp.challenge.api.response;

import com.intercorp.challenge.model.IcClient;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@ApiModel("Client List Response")
public class IcClientListResponse extends IcApiResponse {
    private List<IcClient> clientList;

    public IcClientListResponse(List<IcClient> clientList, String message) {
        super(message);
        this.clientList = clientList;
    }
}

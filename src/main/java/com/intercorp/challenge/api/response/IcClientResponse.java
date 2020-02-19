package com.intercorp.challenge.api.response;

import com.intercorp.challenge.model.IcClient;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("Client Response")
public class IcClientResponse extends IcApiResponse {
    private IcClient client;

    public IcClientResponse(IcClient client, String message) {
        super(message);
        this.client = client;
    }
}

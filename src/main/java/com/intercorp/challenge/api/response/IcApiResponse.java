package com.intercorp.challenge.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class IcApiResponse {
    private String responseMessage;

    protected IcApiResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

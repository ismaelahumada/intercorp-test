package com.intercorp.challenge.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter @Setter
public class ValidData {
    private boolean isValid;

    private String errorMessage = "";

    public void setErrorMessage(String errorMessage) {
        if (StringUtils.isNotEmpty(errorMessage)) {
            this.errorMessage += StringUtils.isEmpty(this.errorMessage) ? errorMessage : ", " + errorMessage;
        }
    }
}

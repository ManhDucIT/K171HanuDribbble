package com.example.driblesetting.webservice.inputs;

import com.example.driblesetting.webservice.DribbbleAPIs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenInput extends BaseInput {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
    @JsonProperty("code")
    private String code;

    @Override
    public String getResource() {
        return DribbbleAPIs.GET_ACCESS_TOKEN;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonProperty("client_id")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    @JsonProperty("client_secret")
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

}

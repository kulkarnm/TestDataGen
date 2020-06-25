package com.testgen.userjourney.config.dataset;

import java.util.ArrayList;
import java.util.List;

public class RequestConfig {
    private String requestId;
    private List<RequestParam> requestParams;
    private List<ResponseParam> responseParams;

    public RequestConfig(String requestId){
        this.requestId = requestId;
        this.requestParams = new ArrayList<>();
        this.responseParams = new ArrayList<>();
    }

    public void addToRequestParams(RequestParam requestParam){
        this.requestParams.add(requestParam);
    }

    public void setResponseParams(List<ResponseParam> responseParams){
        this.responseParams = responseParams;
    }
    public void addToResponseParams(ResponseParam responseParam){
        this.responseParams.add(responseParam);
    }

    public String getRequestId() {
        return requestId;
    }

    public List<RequestParam> getRequestParams() {
        return requestParams;
    }

    public List<ResponseParam> getResponseParams() {
        return responseParams;
    }

    @Override
    public String toString() {
        return "RequestConfig{" +
                "requestId='" + requestId + '\'' +
                ", requestParams=" + requestParams +
                ", responseParams=" + responseParams +
                '}';
    }
}

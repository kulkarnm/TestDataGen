package com.testgen.userjourney.config.dataset;

import java.util.ArrayList;
import java.util.List;

public class DatasetConfig {
    private String requestId;
    private List<RequestParam> requestParams;
    private List<ResponseParam> responseParams;
    private String processId;

    public DatasetConfig(String requestId){
        this.requestId = requestId;
        this.requestParams = new ArrayList<>();
        this.responseParams = new ArrayList<>();
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public void addToRequestParams(RequestParam requestParam){
        this.requestParams.add(requestParam);
    }

    public void addToResponseParams(ResponseParam responseParam){
        this.responseParams.add(responseParam);
    }
}

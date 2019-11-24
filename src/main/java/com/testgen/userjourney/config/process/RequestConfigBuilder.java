package com.testgen.userjourney.config.process;

import com.testgen.userjourney.config.dataset.RequestConfig;
import com.testgen.userjourney.config.dataset.ResponseParam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestConfigBuilder {
    private String requestId;
    private List<RequestParamBuilder> requestParamBuilders;
    private List<ResponseParam> responseParams;

    public RequestConfigBuilder() {
        this.requestParamBuilders = new ArrayList<>();
        this.responseParams = new ArrayList<>();
    }

    public List<ResponseParam> getResponseParams() {
        return responseParams;
    }

    public String getRequestId() {
        return requestId;
    }

    public List<RequestParamBuilder> getRequestParamBuilders() {
        return requestParamBuilders;
    }

    public RequestConfig buildRequestConfig() {
        RequestConfig requestConfig = new RequestConfig(this.getRequestId());
        List<RequestParamBuilder> paramBuilders = this.getRequestParamBuilders();
        for (RequestParamBuilder builder : paramBuilders) {
            requestConfig.addToRequestParams(builder.buildRequestParam());
        }
        requestConfig.setResponseParams(this.getResponseParams());
        return requestConfig;
    }

    public RequestConfigBuilder buildRequestConfigBuilder(JSONObject item, String datasetElementName) {
        if (item.has("requestId")) {
            String requestId = item.getString("requestId");
            if (datasetElementName.equals(requestId)) {
                this.requestId = requestId;
                JSONArray childParamJsons = item.getJSONArray("requestParams");
                for (int j = 0; j < childParamJsons.length(); j++) {
                    JSONObject param = childParamJsons.getJSONObject(j);
                    RequestParamBuilder requestParamBuilder = new RequestParamBuilder();
                    this.addToRequestParams(requestParamBuilder.buildRequestParamBuilder(param));
                }
                JSONArray childResponseParamJsons = item.getJSONArray("responseParams");
                for (int k = 0; k < childResponseParamJsons.length(); k++) {
                    JSONObject responsePram = childResponseParamJsons.getJSONObject(k);
                    this.addToResponseParams(buildResponseParam(responsePram));
                }
            }
            return this;
        } else {
            throw new RuntimeException("requestId is missing");
        }
    }

    public void addToRequestParams(RequestParamBuilder requestParam) {
        this.requestParamBuilders.add(requestParam);
    }

    public void addToResponseParams(ResponseParam responseParam) {
        this.responseParams.add(responseParam);
    }

    private ResponseParam buildResponseParam(JSONObject param) {
        String paramName = param.getString("paramName");
        String paramType = param.getString("paramType");
        return new ResponseParam(paramName, paramType);
    }

} 

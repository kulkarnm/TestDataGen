package com.testgen.userjourney.config.dataset;

public class ResponseParam {
    private String paramName;
    private String paramType;

    public ResponseParam(String paramName, String paramType) {
        this.paramName = paramName;
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamType() {
        return paramType;
    }
}

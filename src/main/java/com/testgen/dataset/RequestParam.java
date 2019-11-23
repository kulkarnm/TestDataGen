package com.testgen.dataset;

public class RequestParam {
    private String paramName;
    private String paramType;
    private ParamSource paramSource;
    private String permissibleValueRange;

    public RequestParam(String paramName, String paramType, ParamSource paramSource, String permissibleValueRange) {
        this.paramName = paramName;
        this.paramType = paramType;
        this.paramSource = paramSource;
        this.permissibleValueRange = permissibleValueRange;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public ParamSource getParamSource() {
        return paramSource;
    }

    public String getPermissibleValueRange() {
        return permissibleValueRange;
    }
}

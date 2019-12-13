package com.testgen.userjourney.cache;


import org.json.JSONObject;

public class CacheSegment {

    private String paramName;
    private String paramType;
    private JSONObject paramValue1;
    private String paramValue;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public JSONObject getParamValue1() {
        return paramValue1;
    }

    public void setParamValue1(JSONObject paramValue1) {
        this.paramValue1 = paramValue1;
    }


    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public CacheSegment createCacheSegmentFromJSON(String processName, String paramType, String paramValue){
        CacheSegment cacheSegment = new CacheSegment();
        cacheSegment.setParamName(processName);
        cacheSegment.setParamType(paramType);
        cacheSegment.setParamValue(paramValue);
        return cacheSegment;
    }

}

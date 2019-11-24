package com.testgen.userjourney.config.dataset;

import java.util.ArrayList;
import java.util.List;

public class RequestParam {
    private String paramName;
    private String paramType;
    private ParamSource paramSource;
    private String permissibleValueRange;
    private List<RequestParam> childRequestPrams;
    private int repeatChildren;
    private String interRelation;

    public RequestParam(String paramName, String paramType, ParamSource paramSource, String permissibleValueRange) {
        this.paramName = paramName;
        this.paramType = paramType;
        this.paramSource = paramSource;
        this.permissibleValueRange = permissibleValueRange;
        this.childRequestPrams=null;
        this.repeatChildren=0;
        this.interRelation = null;
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

    public void setRepeatChildren(int repeatChildren) {
        this.repeatChildren = repeatChildren;
    }

    public void setInterRelation(String interRelation) {
        this.interRelation = interRelation;
    }

    public List<RequestParam> getChildRequestPrams() {
        return childRequestPrams;
    }

    public int getRepeatChildren() {
        return repeatChildren;
    }

    public String getInterRelation() {
        return interRelation;
    }

    public void addToChildRequestParams(RequestParam requestParam){
        if(null == childRequestPrams){
            this.childRequestPrams= new ArrayList<>();
        }
        this.childRequestPrams.add(requestParam);
    }
}

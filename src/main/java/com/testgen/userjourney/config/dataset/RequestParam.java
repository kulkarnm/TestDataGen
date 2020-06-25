package com.testgen.userjourney.config.dataset;

import java.util.ArrayList;
import java.util.List;

public class RequestParam {
    private String paramName;
    private String paramType;
    private ParamSource paramSource;
    private String startingWithStr;
    private String permissibleValueRange;
    private List<RequestParam> startingWith;
    private int repeatChildren;
    private String interRelation;

    public RequestParam(String paramName, String paramType, ParamSource paramSource) {
        this.paramName = paramName;
        this.paramType = paramType;
        this.paramSource = paramSource;
    }

    public void setPermissibleValueRange(String permissibleValueRange) {
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

    public void setRepeatChildren(int repeatChildren) {
        this.repeatChildren = repeatChildren;
    }

    public void setInterRelation(String interRelation) {
        this.interRelation = interRelation;
    }

    public void setStartingWithStr(String startingWithStr) {
        this.startingWithStr = startingWithStr;
    }

    public int getRepeatChildren() {
        return repeatChildren;
    }

    public String getInterRelation() {
        return interRelation;
    }

    public String getStartingWithStr() {
        return startingWithStr;
    }

    public List<RequestParam> getStartingWith() {
        return startingWith;
    }

    public void addToChildRequestParams(RequestParam requestParam){
        if(null == startingWith){
            this.startingWith= new ArrayList<>();
        }
        this.startingWith.add(requestParam);
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "paramName='" + paramName + '\'' +
                ", paramType='" + paramType + '\'' +
                ", paramSource=" + paramSource +
                ", startingWithStr='" + startingWithStr + '\'' +
                ", permissibleValueRange='" + permissibleValueRange + '\'' +
                ", startingWith=" + startingWith +
                ", repeatChildren=" + repeatChildren +
                ", interRelation='" + interRelation + '\'' +
                '}';
    }
}

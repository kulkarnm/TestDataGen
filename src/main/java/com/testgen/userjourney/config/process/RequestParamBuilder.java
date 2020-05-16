package com.testgen.userjourney.config.process;

import com.testgen.userjourney.config.dataset.ParamSource;
import com.testgen.userjourney.config.dataset.RequestParam;
import com.testgen.userjourney.config.dataset.ResponseParam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestParamBuilder {
    private String paramName;
    private String paramType;
    private ParamSource paramSource;
    private String startingWithStr;
    private String permissibleValueRange;
    private List<RequestParamBuilder> startingWith;

    private int repeatChildren;
    private String interRelation;

    public RequestParamBuilder() {
    }

    private void setParamName(String paramName) {
        this.paramName = paramName;
    }

    private void setParamType(String paramType) {
        this.paramType = paramType;
    }

    private void setParamSource(ParamSource paramSource) {
        this.paramSource = paramSource;
    }

    private void setPermissibleValueRange(String permissibleValueRange) {
        this.permissibleValueRange = permissibleValueRange;
    }

    private void setRepeatChildren(int repeatChildren) {
        this.repeatChildren = repeatChildren;
    }

    private void setInterRelation(String interRelation) {
        this.interRelation = interRelation;
    }

    private void addToStartingWith(RequestParamBuilder childRequestParamBuilder) {
        this.startingWith.add(childRequestParamBuilder);
    }

    private void setStartingWithStr(String startingWith) {
        this.startingWithStr = startingWith;
    }

    private void setStartingWith(List<RequestParamBuilder> startingWith) {
        this.startingWith = startingWith;
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

    public String getStartingWithStr() {
        return startingWithStr;
    }

    public String getPermissibleValueRange() {
        return permissibleValueRange;
    }

    public List<RequestParamBuilder> getStartingWith() {
        return startingWith;
    }


    public int getRepeatChildren() {
        return repeatChildren;
    }

    public String getInterRelation() {
        return interRelation;
    }

    public RequestParam buildRequestParam(){
        RequestParam requestParam= new RequestParam(this.getParamName(),this.getParamType(),this.getParamSource());
        requestParam.setInterRelation(this.getInterRelation());
        requestParam.setPermissibleValueRange(this.getPermissibleValueRange());
        requestParam.setRepeatChildren(this.getRepeatChildren());
        requestParam.setStartingWithStr(this.getStartingWithStr());
        List<RequestParamBuilder> childrenBuilders = this.getStartingWith();
        if(null != childrenBuilders && !childrenBuilders.isEmpty()) {
            for (RequestParamBuilder builder : childrenBuilders) {
                requestParam.addToChildRequestParams(builder.buildRequestParam());
            }
        }
        return requestParam;
    }
    public RequestParamBuilder buildRequestParamBuilder(JSONObject param) {
        if (param.has("paramName")) {
            this.setParamName(param.getString("paramName"));
        } else {
            throw new RuntimeException("request param name is absent");
        }
        if (param.has("paramType")) {
            this.setParamType(param.getString("paramType"));
        } else {
            throw new RuntimeException("request param type is absent");
        }
        if (param.has("paramSource")) {
            String paramSource = param.getString("paramSource");

            switch (paramSource) {
                case "external":
                    this.setParamSource(ParamSource.EXTERNAL);
                    break;
                case "external-const":
                    this.setParamSource(ParamSource.EXTERNAL_CONST);
                    break;
                case "internal-process-output":
                    this.setParamSource(ParamSource.INTERNAL_PROCESS_OUTPUT);
            }
        } else {
            throw new RuntimeException("request param source is absent");
        }
        this.setPermissibleValueRange("");
        if (param.has("permissibleValueRange")) {
            this.setPermissibleValueRange(param.getString("permissibleValueRange"));
        }
        this.setInterRelation("");
        if (param.has("relation")) {
            this.setInterRelation(param.getString("relation"));
        }
        this.setStartingWithStr("");
        this.setStartingWith(null);
        if (param.has("startingWith")) {
            Object startingWithObj = param.get("startingWith");
            if (startingWithObj instanceof String) {
                this.setStartingWithStr(param.getString("startingWith"));
            } else {
                JSONArray children = param.getJSONArray("startingWith");
                List<RequestParamBuilder> childrenParams = new ArrayList<>();
                for (int i = 0; i < children.length(); i++) {
                    RequestParamBuilder childRequestParamBuilder = new RequestParamBuilder();
                    childrenParams.add(childRequestParamBuilder.buildRequestParamBuilder(children.getJSONObject(i)));
                }
                this.setStartingWith(childrenParams);
            }
        }
        this.setRepeatChildren(0);
        if (param.has("repeat")) {
            this.setRepeatChildren(param.getInt("repeat"));
        }
        return this;
    }
}

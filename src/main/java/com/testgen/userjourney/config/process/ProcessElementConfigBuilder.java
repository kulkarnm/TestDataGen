package com.testgen.userjourney.config.process;

import com.testgen.userjourney.config.dataset.DatasetConfig;
import com.testgen.userjourney.config.dataset.ParamSource;
import com.testgen.userjourney.config.dataset.RequestParam;
import com.testgen.userjourney.config.dataset.ResponseParam;
import com.testgen.parser.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcessElementConfigBuilder {

    private String processId;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private DatasetConfig dataSetConfig;
    private List<ProcessElementConfigBuilder> childProcesses;
    private JsonParser parser;

    public ProcessElementConfigBuilder() {
        this.processId = "-1";
        this.processExecutionType = ProcessExecutionType.ONETIME;
        this.executionCount = 1;
        this.multiCasting = false;
        this.childProcesses = new ArrayList<>();
        this.parser = new JsonParser();
    }

    public void processExecutionType(ProcessExecutionType processExecutionType) {
        this.processExecutionType = processExecutionType;
    }

    public void executionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public void multiCasting(boolean multiCasting) {
        this.multiCasting = multiCasting;
    }

    public ProcessElementConfigBuilder dataSetConfig(DatasetConfig dataSetConfig) {
        this.dataSetConfig = dataSetConfig;
        return this;
    }

    public ProcessElementConfigBuilder childProcessRefs(List<ProcessElementConfigBuilder> processRefs) {
        this.childProcesses = processRefs;
        return this;
    }

    public void processId(String processId) {
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public ProcessExecutionType getProcessExecutionType() {
        return processExecutionType;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public boolean isMultiCasting() {
        return multiCasting;
    }

    public DatasetConfig getDataSetConfig() {
        return dataSetConfig;
    }

    public List<ProcessElementConfigBuilder> getChildProcesses() {
        return childProcesses;
    }

    public ProcessElementConfig build() {
        ProcessElementConfigBuilder builder = buildProcessElement(parser.parse("process/root-process-config.json"));
        return builder.buildProcessElement();
    }

    private ProcessElementConfig buildProcessElement() {
        AbstractProcessElementConfig processElement = null;
        List<ProcessElementConfigBuilder> childProcesses = this.getChildProcesses();
        if (null != childProcesses && !childProcesses.isEmpty()) {
            processElement = new CompositeProcessConfig(this);
            for (ProcessElementConfigBuilder childElementBuilder : childProcesses) {
                processElement.addToProcessElements(childElementBuilder.build());
            }
        } else {
            processElement = new LeafProcessElementConfig(this);
        }
        return processElement;
    }

    private ProcessElementConfigBuilder buildProcessElement(JSONObject jsonObject) {
        ProcessElementConfigBuilder builder = new ProcessElementConfigBuilder();
        if (jsonObject.has("processId")) {
            String processId = jsonObject.getString("processId");
            builder.processId(processId);
        } else {
            throw new RuntimeException("processId is missing");
        }

        if (jsonObject.has("processExecutionType")) {
            String processExecutionTypeStr = jsonObject.getString("processExecutionType");
            switch (processExecutionTypeStr) {
                case "one-time":
                    builder.processExecutionType(ProcessExecutionType.ONETIME);
                    break;
                case "recurring":
                    builder.processExecutionType(ProcessExecutionType.RECURRING);
            }
        }
        int executionCount = 1;
        if (jsonObject.has("executionCount")) {
            executionCount = jsonObject.getInt("executionCount");
        }
        builder.executionCount(executionCount);
        boolean multiCasting = false;
        if (jsonObject.has("multiCasting")) {
            multiCasting = jsonObject.getBoolean("multiCasting");
        }
        builder.multiCasting(multiCasting);
        if (jsonObject.has("childProcessElements")) {
            JSONArray childProcessElementsJsons = jsonObject.getJSONArray("childProcessElements");
            if (null != childProcessElementsJsons && !childProcessElementsJsons.isEmpty()) {
                for (int i = 0; i < childProcessElementsJsons.length(); i++) {
                    String childProcessRef = childProcessElementsJsons.getJSONObject(i).getString("processRef");
                    System.out.println("childProcessRef: " + childProcessRef);
                    String childProcessDefFileName = "process/" + childProcessRef.substring(0, childProcessRef.indexOf("#")) + ".json";
                    this.childProcesses.add(buildProcessElement(parser.parse(childProcessDefFileName)));
                }
            }
        }

        if (jsonObject.has("dataset-config")) {
            String datasetConfigLocation = jsonObject.getString("dataset-config");
            String datasetConfigFileName = "dataset/" + datasetConfigLocation.substring(0, datasetConfigLocation.indexOf("#")) + ".json";
            String datasetElementName = datasetConfigLocation.substring(datasetConfigLocation.indexOf("#") + 1);
            this.dataSetConfig = buildDatasetConfig(parser.parse(datasetConfigFileName), datasetElementName);
        } else {
            //for now do nothing.. some writes may not have requests and responses
        }
        return builder;
    }

    private DatasetConfig buildDatasetConfig(JSONObject jsonObject, String datasetElementName) {
        JSONArray array = new JSONArray(jsonObject);
        JSONArray jsonPersonData = array.getJSONArray(1);
        for (int i = 0; i < jsonPersonData.length(); i++) {
            JSONObject item = jsonPersonData.getJSONObject(i);
            if (item.has("requestId")) {
                String requestId = item.getString("requestId");
                if (datasetElementName.equals(requestId)) {
                    DatasetConfig datasetConfig = new DatasetConfig(requestId);
                    JSONArray childParamJsons = item.getJSONArray("requestParams");
                    for (int j = 0; j < childParamJsons.length(); j++) {
                        JSONObject param = childParamJsons.getJSONObject(i);
                        datasetConfig.addToRequestParams(buildRequestParam(param));
                    }
                    JSONArray childResponseParamJsons = item.getJSONArray("responseParams");
                    for (int k = 0; k < childResponseParamJsons.length(); k++) {
                        JSONObject responsePram = childResponseParamJsons.getJSONObject(i);
                        datasetConfig.addToResponseParams(buildResponseParam(responsePram));
                    }
                }
                return dataSetConfig;
            } else {
                throw new RuntimeException("requestId is missing");
            }
        }
        return null;
    }

    private RequestParam buildRequestParam(JSONObject param) {
        String paramName = param.getString("paramName");
        String paramType = param.getString("paramType");
        String paramSource = param.getString("paramSource");
        ParamSource paramSourceEnum = null;
        switch (paramSource) {
            case "external":
                paramSourceEnum = ParamSource.EXTERNAL;
                break;
            case "internal-process-output":
                paramSourceEnum = ParamSource.INTERNAL_PROCESS_OUTPUT;
        }
        String permissibleValueRange = param.getString("permissibleValueRange");
        RequestParam requestParam = new RequestParam(paramName, paramType, paramSourceEnum, permissibleValueRange);
        return requestParam;
    }

    private ResponseParam buildResponseParam(JSONObject param) {
        String paramName = param.getString("paramName");
        String paramType = param.getString("paramType");
        return new ResponseParam(paramName, paramType);
    }

    public static void main(String[] args) {
        ProcessElementConfigBuilder builder = new ProcessElementConfigBuilder();
        ProcessElementConfig processElementConfig = builder.build();
    }
}


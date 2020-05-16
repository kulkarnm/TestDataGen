package com.testgen.userjourney.config.process;

import com.testgen.exceptions.NoDataSetDefinitionForProcessException;
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
    private RequestConfigBuilder requestConfigBuilder;
    private List<ProcessElementConfigBuilder> childProcesses;
    private static JsonParser parser = new JsonParser();
    ;

    public ProcessElementConfigBuilder() {
        this.processId = "-1";
        this.processExecutionType = ProcessExecutionType.ONETIME;
        this.executionCount = 1;
        this.multiCasting = false;
        this.childProcesses = new ArrayList<>();
    }

    public ProcessElementConfigBuilder processExecutionType(ProcessExecutionType processExecutionType) {
        this.processExecutionType = processExecutionType;
        return this;
    }

    public ProcessElementConfigBuilder executionCount(int executionCount) {
        this.executionCount = executionCount;
        return this;
    }

    public ProcessElementConfigBuilder multiCasting(boolean multiCasting) {
        this.multiCasting = multiCasting;
        return this;
    }

    public ProcessElementConfigBuilder dataSetConfig(RequestConfigBuilder requestConfigBuilder) {
        this.requestConfigBuilder = requestConfigBuilder;
        return this;
    }

    public ProcessElementConfigBuilder childProcessRefs(List<ProcessElementConfigBuilder> processRefs) {
        this.childProcesses = processRefs;
        return this;
    }

    public ProcessElementConfigBuilder processId(String processId) {
        this.processId = processId;
        return this;
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

    public RequestConfigBuilder getRequestConfigBuilder() {
        return this.requestConfigBuilder;
    }

    public List<ProcessElementConfigBuilder> getChildProcesses() {
        return childProcesses;
    }

/*
    public void build(String processDefinitionFile) throws NoDataSetDefinitionForProcessException {
        buildTestRequests(processDefinitionFile);
    }
*/

/*
    private ProcessElementConfig buildProcessElement() throws NoDataSetDefinitionForProcessException {
        AbstractProcessElementConfig processElement = null;
        List<ProcessElementConfigBuilder> childProcesses = this.getChildProcesses();
        if (null != childProcesses && !childProcesses.isEmpty()) {
            processElement = new CompositeProcessConfig(this);
            for (ProcessElementConfigBuilder childElementBuilder : childProcesses) {
                processElement.addToProcessElements(childElementBuilder.buildProcessElement());
            }
        } else {
            if(null != this.getRequestConfigBuilder()) {
                processElement = new LeafProcessElementConfig(this);
            }else{
                throw NoDataSetDefinitionForProcessException.build();
            }
        }
        return processElement;
    }
*/

    public static ProcessElementConfigBuilder buildTestRequests(String processDefinitionFile) throws NoDataSetDefinitionForProcessException {
        JSONObject jsonObject = null;
        if (null != processDefinitionFile) {
            jsonObject = parser.parse(processDefinitionFile);
        } else {
            jsonObject = parser.parse("processes/root-process-config.json");
        }
        ProcessElementConfigBuilder processElementConfigBuilder = new ProcessElementConfigBuilder();
        if (jsonObject.has("processId")) {
            String processId = jsonObject.getString("processId");
            processElementConfigBuilder.processId(processId);
        } else {
            throw new RuntimeException("processId is missing");
        }

        if (jsonObject.has("processExecutionType")) {
            String processExecutionTypeStr = jsonObject.getString("processExecutionType");
            switch (processExecutionTypeStr) {
                case "one-time":
                    processElementConfigBuilder.processExecutionType(ProcessExecutionType.ONETIME);
                    break;
                case "recurring":
                    processElementConfigBuilder.processExecutionType(ProcessExecutionType.RECURRING);
                    break;
                default:
                    processElementConfigBuilder.processExecutionType(ProcessExecutionType.ONETIME);
            }
        }
        int executionCount = 1;
        if (jsonObject.has("executionCount")) {
            executionCount = jsonObject.getInt("executionCount");
        }
        processElementConfigBuilder.executionCount(executionCount);
        boolean multiCasting = false;
        if (jsonObject.has("multiCasting")) {
            multiCasting = jsonObject.getBoolean("multiCasting");
        }
        processElementConfigBuilder.multiCasting(multiCasting);

        if (jsonObject.has("childProcessElements")) {
            JSONArray childProcessElementsJsons = jsonObject.getJSONArray("childProcessElements");
            if (null != childProcessElementsJsons && !childProcessElementsJsons.isEmpty()) {
                for (int i = 0; i < childProcessElementsJsons.length(); i++) {
                    String childProcessRef = childProcessElementsJsons.getJSONObject(i).getString("processRef");
                    processElementConfigBuilder.childProcesses.add(buildTestRequests("processes/" + childProcessRef.substring(0, childProcessRef.indexOf("#")) + ".json"));
                }
            }
        } else {
            //dataset config is only allowed for leaf elements
            if (jsonObject.has("dataset-config")) {
                String datasetConfigLocation = jsonObject.getString("dataset-config");
                RequestConfigBuilder requestConfigBuilder = readDataSetConfig(datasetConfigLocation);
                RequestBuilder requestBuilder = new RequestBuilder();
                requestBuilder.buildRequest(requestConfigBuilder);
            } else {
                throw NoDataSetDefinitionForProcessException.build();
            }
        }
        return processElementConfigBuilder;
    }

    private static RequestConfigBuilder readDataSetConfig(String datasetConfigLocation) {
        String datasetConfigFileName = "datasets/" + datasetConfigLocation.substring(0, datasetConfigLocation.indexOf("#")) + ".json";
        String datasetElementName = datasetConfigLocation.substring(datasetConfigLocation.indexOf("#") + 1);
        RequestConfigBuilder requestConfigBuilder = new RequestConfigBuilder();
        return requestConfigBuilder.buildRequestConfigBuilder(parser.parse(datasetConfigFileName), datasetElementName);
    }
}


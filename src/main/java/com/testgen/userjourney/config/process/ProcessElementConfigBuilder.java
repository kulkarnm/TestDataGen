package com.testgen.userjourney.config.process;

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
    private JsonParser parser;

    public ProcessElementConfigBuilder() {
        this.processId = "-1";
        this.processExecutionType = ProcessExecutionType.ONETIME;
        this.executionCount = 1;
        this.multiCasting = false;
        this.childProcesses = new ArrayList<>();
        this.parser = new JsonParser();
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

    public ProcessElementConfig build() {
        ProcessElementConfigBuilder builder = buildProcessElement(parser.parse("processes/root-process-config.json"));
        return builder.buildProcessElement();
    }

    private ProcessElementConfig buildProcessElement() {
        AbstractProcessElementConfig processElement = null;
        List<ProcessElementConfigBuilder> childProcesses = this.getChildProcesses();
        if (null != childProcesses && !childProcesses.isEmpty()) {
            processElement = new CompositeProcessConfig(this);
            for (ProcessElementConfigBuilder childElementBuilder : childProcesses) {
                processElement.addToProcessElements(childElementBuilder.buildProcessElement());
            }
        } else {
            processElement = new LeafProcessElementConfig(this);
        }
        return processElement;
    }

    private ProcessElementConfigBuilder buildProcessElement(JSONObject jsonObject) {
        if (jsonObject.has("processId")) {
            String processId = jsonObject.getString("processId");
            this.processId(processId);
        } else {
            throw new RuntimeException("processId is missing");
        }

        if (jsonObject.has("processExecutionType")) {
            String processExecutionTypeStr = jsonObject.getString("processExecutionType");
            switch (processExecutionTypeStr) {
                case "one-time":
                    this.processExecutionType(ProcessExecutionType.ONETIME);
                    break;
                case "recurring":
                    this.processExecutionType(ProcessExecutionType.RECURRING);
            }
        }
        int executionCount = 1;
        if (jsonObject.has("executionCount")) {
            executionCount = jsonObject.getInt("executionCount");
        }
        this.executionCount(executionCount);
        boolean multiCasting = false;
        if (jsonObject.has("multiCasting")) {
            multiCasting = jsonObject.getBoolean("multiCasting");
        }
        this.multiCasting(multiCasting);
        if (jsonObject.has("childProcessElements")) {
            JSONArray childProcessElementsJsons = jsonObject.getJSONArray("childProcessElements");
            if (null != childProcessElementsJsons && !childProcessElementsJsons.isEmpty()) {
                for (int i = 0; i < childProcessElementsJsons.length(); i++) {
                    String childProcessRef = childProcessElementsJsons.getJSONObject(i).getString("processRef");
                    //System.out.println("childProcessRef: " + childProcessRef);
                    String childProcessDefFileName = "processes/" + childProcessRef.substring(0, childProcessRef.indexOf("#")) + ".json";
                    System.out.println("childProcessFileName: " + childProcessDefFileName);
                    ProcessElementConfigBuilder childBuilder = new ProcessElementConfigBuilder();
                    this.childProcesses.add(childBuilder.buildProcessElement(parser.parse(childProcessDefFileName)));
                }
            }
        }

        if (jsonObject.has("dataset-config")) {
            String datasetConfigLocation = jsonObject.getString("dataset-config");
            String datasetConfigFileName = "datasets/" + datasetConfigLocation.substring(0, datasetConfigLocation.indexOf("#")) + ".json";
            String datasetElementName = datasetConfigLocation.substring(datasetConfigLocation.indexOf("#") + 1);
            RequestConfigBuilder requestConfigBuilder = new RequestConfigBuilder();
            System.out.println("datasetConfigFileName: " + datasetConfigFileName);
            this.requestConfigBuilder = requestConfigBuilder.buildRequestConfigBuilder(parser.parse(datasetConfigFileName), datasetElementName);
        } else {
            //for now do nothing.. some writes may not have requests and responses
        }
        return this;
    }




}


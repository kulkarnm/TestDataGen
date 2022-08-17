package com.testgen.userjourney.config.process;

import com.testgen.exceptions.NoDataSetDefinitionForProcessException;
import com.testgen.parser.JsonParser;
import com.testgen.userjourney.config.dataset.RequestConfig;
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
    public void childProcesses(List<ProcessElementConfigBuilder> childProcesses){
        this.childProcesses=childProcesses;
    }
    public void requestConfigBuilder(RequestConfigBuilder requestConfigBuilder) {
        this.requestConfigBuilder = requestConfigBuilder;
    }

    public static AbstractProcessElementConfig execute(String processDefinitionFile) throws NoDataSetDefinitionForProcessException {
        ProcessElementConfigBuilder rootProcessElementConfiguration = readProcessConfig(processDefinitionFile);
        AbstractProcessElementConfig rootElement = rootProcessElementConfiguration.build();
       // print(rootElement);
        return rootElement;
    }

    public AbstractProcessElementConfig build() throws NoDataSetDefinitionForProcessException {
        AbstractProcessElementConfig processElement = null;
        List<ProcessElementConfigBuilder> childProcesses = this.getChildProcesses();
        if (null != childProcesses && !childProcesses.isEmpty()) {
            processElement = new CompositeProcessConfig(this);
            for (ProcessElementConfigBuilder childElementBuilder : childProcesses) {
                processElement.addToProcessElements(childElementBuilder.build());
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

    private static JSONObject readJsonFile(String processDefinitionFile){
        return parser.parse(processDefinitionFile);
    }
    private static String readProcessId(JSONObject jsonObject){
        if (jsonObject.has("processId")) {
            return jsonObject.getString("processId");
            //processElementConfigBuilder.processId(processId);
        } else {
            throw new RuntimeException("processId is missing");
        }
    }

    private static ProcessExecutionType readProcessExecutionType(JSONObject jsonObject){
        if (jsonObject.has("processExecutionType")) {
            String processExecutionTypeStr = jsonObject.getString("processExecutionType");
            switch (processExecutionTypeStr) {
                case "one-time":
                    //processElementConfigBuilder.processExecutionType(ProcessExecutionType.ONETIME);
                    return ProcessExecutionType.ONETIME;
                case "recurring":
                    return ProcessExecutionType.RECURRING;
                default:
                    return ProcessExecutionType.ONETIME;
            }
        }
        return ProcessExecutionType.ONETIME;
    }
    private static int readExecutionCount(JSONObject jsonObject){
        int executionCount = 1;
        if (jsonObject.has("executionCount")) {
            executionCount = jsonObject.getInt("executionCount");
        }
        return executionCount;
    }

    private static boolean readMulticasting(JSONObject jsonObject){
        boolean multiCasting = false;
        if (jsonObject.has("multiCasting")) {
            multiCasting = jsonObject.getBoolean("multiCasting");
        }
        return multiCasting;
    }
    private static List<ProcessElementConfigBuilder> buildChildProcesses(JSONObject jsonObject)  throws NoDataSetDefinitionForProcessException {
        List<ProcessElementConfigBuilder> childProcesses = new ArrayList<>();
        if (jsonObject.has("childProcessElements")) {
            JSONArray childProcessElementsJsons = jsonObject.getJSONArray("childProcessElements");
            if (null != childProcessElementsJsons && childProcessElementsJsons.length()>0) {
                for (int i = 0; i < childProcessElementsJsons.length(); i++) {
                    String childProcessRef = childProcessElementsJsons.getJSONObject(i).getString("processRef");
                    childProcesses.add(readProcessConfig("processes/" + childProcessRef.substring(0, childProcessRef.indexOf("#")) + ".json"));
                }
            }
            return childProcesses;
        }
        return null;
    }

    private static  RequestConfigBuilder readRequestConfigBuilder(JSONObject jsonObject) throws NoDataSetDefinitionForProcessException {
            //dataset config is only allowed for leaf elements
            if (jsonObject.has("dataset-config")) {
                String datasetConfigLocation = jsonObject.getString("dataset-config");
                RequestConfigBuilder requestConfigBuilder = readDataSetConfig(datasetConfigLocation);
                //to be done in a multithreaded way
                //RequestBuilder requestBuilder = new RequestBuilder();
                //requestBuilder.buildRequest(requestConfigBuilder);
               return requestConfigBuilder;
            } else {
                throw NoDataSetDefinitionForProcessException.build();
            }
    }
    public static ProcessElementConfigBuilder readProcessConfig(String processDefinitionFile) throws NoDataSetDefinitionForProcessException {
        JSONObject jsonObject = readJsonFile(processDefinitionFile);
        ProcessElementConfigBuilder processElementConfigBuilder = new ProcessElementConfigBuilder();
        processElementConfigBuilder.processId(readProcessId(jsonObject));
        processElementConfigBuilder.processExecutionType(readProcessExecutionType(jsonObject));
        processElementConfigBuilder.executionCount(readExecutionCount(jsonObject));
        processElementConfigBuilder.multiCasting(readMulticasting(jsonObject));
        List<ProcessElementConfigBuilder> childProcesses = buildChildProcesses(jsonObject);
        if(null != childProcesses && !childProcesses.isEmpty()){
            processElementConfigBuilder.childProcesses(childProcesses);
        }else{
            processElementConfigBuilder.dataSetConfig(readRequestConfigBuilder(jsonObject));
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


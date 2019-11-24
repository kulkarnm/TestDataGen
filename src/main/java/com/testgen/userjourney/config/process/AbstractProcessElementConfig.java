package com.testgen.userjourney.config.process;

import com.testgen.userjourney.config.dataset.RequestConfig;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProcessElementConfig implements ProcessElementConfig {
    private String processId;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private RequestConfig dataSetConfig;
    private List<ProcessElementConfig> childProcesses;

    public AbstractProcessElementConfig(String processId) {
        this.processId = processId;
    }

    public AbstractProcessElementConfig(String processId, ProcessExecutionType processExecutionType, int executionCount, boolean multiCasting, RequestConfig dataSetConfig) {
        this.processId = processId;
        this.processExecutionType = processExecutionType;
        this.executionCount = executionCount;
        this.multiCasting = multiCasting;
        this.dataSetConfig = dataSetConfig;
        this.childProcesses = new ArrayList<>();
    }

    public String getProcessId() {
        return processId;
    }

    private void setProcessId(String processId) {
        this.processId = processId;
    }

    public ProcessExecutionType getProcessExecutionType() {
        return processExecutionType;
    }

    private void setProcessExecutionType(ProcessExecutionType processExecutionType) {
        this.processExecutionType = processExecutionType;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    private void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public boolean isMultiCasting() {
        return multiCasting;
    }

    private void setMultiCasting(boolean multiCasting) {
        this.multiCasting = multiCasting;
    }

    public RequestConfig getDataSetConfig() {
        return dataSetConfig;
    }

    private void setDataSetConfig(RequestConfig dataSetConfig) {
        this.dataSetConfig = dataSetConfig;
    }

    public List<ProcessElementConfig> getChildProcesses() {
        return childProcesses;
    }

    private void setChildProcesses(List<ProcessElementConfig> childProcesses) {
        this.childProcesses = childProcesses;
    }

    public void addToProcessElements(ProcessElementConfig processElementConfig){
        this.childProcesses.add(processElementConfig);
    }
}

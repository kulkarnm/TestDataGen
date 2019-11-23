package com.testgen.userjourney;

import com.testgen.dataset.DatasetConfig;

import java.util.List;

public abstract class AbstractProcessElement implements ProcessElement {
    private String processId;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private DatasetConfig dataSetConfig;
    private List<ProcessElement> childProcesses;

    public AbstractProcessElement(String processId) {
        this.processId = processId;
    }

    public AbstractProcessElement(String processId, ProcessExecutionType processExecutionType, int executionCount, boolean multiCasting, DatasetConfig dataSetConfig) {
        this.processId = processId;
        this.processExecutionType = processExecutionType;
        this.executionCount = executionCount;
        this.multiCasting = multiCasting;
        this.dataSetConfig = dataSetConfig;
        this.childProcesses = childProcesses;
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

    public DatasetConfig getDataSetConfig() {
        return dataSetConfig;
    }

    private void setDataSetConfig(DatasetConfig dataSetConfig) {
        this.dataSetConfig = dataSetConfig;
    }

    public List<ProcessElement> getChildProcesses() {
        return childProcesses;
    }

    private void setChildProcesses(List<ProcessElement> childProcesses) {
        this.childProcesses = childProcesses;
    }

    public void addToProcessElements(ProcessElement processElement){
        this.childProcesses.add(processElement);
    }
}

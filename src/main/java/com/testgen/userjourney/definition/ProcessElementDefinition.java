package com.testgen.userjourney.definition;

import com.testgen.userjourney.config.process.ProcessExecutionType;

import java.util.List;

public class ProcessElementDefinition {
    private String processId;
    private List<ProcessElementDefinition> parentProcesses;
    private int processLevel;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private ProcessRequest processRequest;
    private ProcessTarget processTarget;
    private ProcessResponse processResponse;
    private List<ProcessElementDefinition> forkedProcessElementDefinitions;

    public ProcessElementDefinition(String processId) {
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<ProcessElementDefinition> getParentProcesses() {
        return parentProcesses;
    }

    public void setParentProcesses(List<ProcessElementDefinition> parentProcesses) {
        this.parentProcesses = parentProcesses;
    }

    public int getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(int processLevel) {
        this.processLevel = processLevel;
    }

    public ProcessExecutionType getProcessExecutionType() {
        return processExecutionType;
    }

    public void setProcessExecutionType(ProcessExecutionType processExecutionType) {
        this.processExecutionType = processExecutionType;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public boolean isMultiCasting() {
        return multiCasting;
    }

    public void setMultiCasting(boolean multiCasting) {
        this.multiCasting = multiCasting;
    }

    public ProcessRequest getProcessRequest() {
        return processRequest;
    }

    public void setProcessRequest(ProcessRequest processRequest) {
        this.processRequest = processRequest;
    }

    public ProcessTarget getProcessTarget() {
        return processTarget;
    }

    public void setProcessTarget(ProcessTarget processTarget) {
        this.processTarget = processTarget;
    }

    public ProcessResponse getProcessResponse() {
        return processResponse;
    }

    public void setProcessResponse(ProcessResponse processResponse) {
        this.processResponse = processResponse;
    }

    public List<ProcessElementDefinition> getForkedProcessElementDefinitions() {
        return forkedProcessElementDefinitions;
    }

    public void setForkedProcessElementDefinitions(List<ProcessElementDefinition> forkedProcessElementDefinitions) {
        this.forkedProcessElementDefinitions = forkedProcessElementDefinitions;
    }
}

package com.testgen.userjourney.definition;

public class ProcessRequest {
    private String processId;
    private int processLevel;
    private String request;

    public ProcessRequest(String processId, String request) {
        this.processId = processId;
        this.request = request;
    }

    public void setProcessLevel(int processLevel) {
        this.processLevel = processLevel;
    }
}

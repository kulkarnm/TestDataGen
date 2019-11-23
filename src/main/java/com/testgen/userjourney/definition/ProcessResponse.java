package com.testgen.userjourney.definition;

public class ProcessResponse {
    private String processId;
    private int processLevel;
    private String response;

    public ProcessResponse(String processId, String response) {
        this.processId = processId;
        this.response = response;
    }

    public void setProcessLevel(int processLevel) {
        this.processLevel = processLevel;
    }
}

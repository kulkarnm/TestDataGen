package com.testgen.userjourney.definition;

public class ProcessTarget {
    private String processId;
    private String baseUrl;
    private String uri;
    private ConnectionProtocol protocol;

    public ProcessTarget(String processId, String baseUrl, String uri, ConnectionProtocol protocol) {
        this.processId = processId;
        this.baseUrl = baseUrl;
        this.uri = uri;
        this.protocol = protocol;
    }

    public String getProcessId() {
        return processId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUri() {
        return uri;
    }

    public ConnectionProtocol getProtocol() {
        return protocol;
    }
}

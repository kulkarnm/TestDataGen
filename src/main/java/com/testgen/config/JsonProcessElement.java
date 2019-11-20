package com.testgen.config;

import com.testgen.userjourney.DatasetConfig;
import com.testgen.userjourney.ProcessElement;
import com.testgen.userjourney.ProcessExecutionType;

import java.util.List;

public abstract class JsonProcessElement implements ProcessElement {
    private String processId;
    private String processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private DatasetConfig dataSetConfig;
    private List<ProcessElement> childProcessElements;


    public static class ProcessElementBuilder {
        private String processId;
        private ProcessExecutionType processExecutionType;
        private int executionCount;
        private boolean multiCasting;
        private DatasetConfig dataSetConfig;
        private List<ProcessElement> childProcessElements;

        public ProcessElementBuilder(String processId,ProcessExecutionType processExecutionType, int executionCount,boolean multiCasting){
            this.processId = processId;
            this.processExecutionType = processExecutionType;
            this.executionCount = executionCount;
            this.multiCasting = multiCasting;
        }

        public ProcessElementBuilder setDataSetConfig(DatasetConfig dataSetConfig){
            this.dataSetConfig =dataSetConfig;
            return this;
        }

        public ProcessElementBuilder setChildProcessElements(List<ProcessElement> childProcessElements){
            this.childProcessElements = childProcessElements;
            return this;
        }
    }
} 

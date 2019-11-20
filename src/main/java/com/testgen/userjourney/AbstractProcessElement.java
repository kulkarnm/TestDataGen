package com.testgen.userjourney;

import java.util.List;

public abstract class AbstractProcessElement implements ProcessElement {
    private String processId;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private DatasetConfig dataSetConfig;
    private List<ProcessElement> childProcesses;


    public static class ProcessElementBuilder {
        private String processId;
        private ProcessExecutionType processExecutionType;
        private int executionCount;
        private boolean multiCasting;
        private DatasetConfig dataSetConfig;
        private List<ProcessElement> childProcesses;

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

        public ProcessElementBuilder setChildProcesses(List<ProcessElement> childProcesses){
            this.childProcesses = childProcesses;
            return this;
        }
    }
} 

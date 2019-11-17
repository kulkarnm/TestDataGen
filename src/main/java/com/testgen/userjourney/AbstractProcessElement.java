package com.testgen.userjourney;

import java.util.List;

public abstract class AbstractProcessElement implements ProcessElement {
    private String processName;
    private ProcessExecutionType processExecutionType;
    private int executionCount;
    private boolean multiCasting;
    private List<DatasetConfig> dataSetConfigs;
    private List<ProcessElement> childProcesses;


    public static class ProcessElementBuilder {
        private String processName;
        private ProcessExecutionType processExecutionType;
        private int executionCount;
        private boolean multiCasting;
        private List<DatasetConfig> dataSetConfigs;
        private List<ProcessElement> childProcesses;

        public ProcessElementBuilder(String processName,ProcessExecutionType processExecutionType, int executionCount,boolean multiCasting){
            this.processName = processName;
            this.processExecutionType = processExecutionType;
            this.executionCount = executionCount;
            this.multiCasting = multiCasting;
        }

        public ProcessElementBuilder setDataSetConfigs(List<DatasetConfig> dataSetConfigs){
            this.dataSetConfigs =dataSetConfigs;
            return this;
        }

        public ProcessElementBuilder setChildProcesses(List<ProcessElement> childProcesses){
            this.childProcesses = childProcesses;
            return this;
        }
    }
} 

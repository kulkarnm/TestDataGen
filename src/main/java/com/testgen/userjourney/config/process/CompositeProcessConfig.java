package com.testgen.userjourney.config.process;


//Composite Child of Assembly Component - Composite Design Pattern
public class CompositeProcessConfig extends AbstractProcessElementConfig {

    public CompositeProcessConfig(ProcessElementConfigBuilder builder) {
        super(builder.getProcessId(),builder.getProcessExecutionType(),builder.getExecutionCount(),builder.isMultiCasting(),builder.getDataSetConfig());
    }
}

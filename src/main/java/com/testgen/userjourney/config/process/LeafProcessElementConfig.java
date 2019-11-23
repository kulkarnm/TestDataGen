package com.testgen.userjourney.config.process;

//Composite Child of Assembly Component - Composite Design Pattern
public class LeafProcessElementConfig extends AbstractProcessElementConfig {

    public LeafProcessElementConfig(ProcessElementConfigBuilder builder) {
        super(builder.getProcessId(),builder.getProcessExecutionType(),builder.getExecutionCount(),builder.isMultiCasting(),builder.getDataSetConfig());
    }

    @Override
    public void addToProcessElements(ProcessElementConfig processElementConfig) {
        //do nothing;
    }
}

package com.testgen.userjourney;


import java.util.*;

//Composite Child of Assembly Component - Composite Design Pattern
public class CompositeProcess extends AbstractProcessElement {

    public CompositeProcess(ProcessElementBuilder builder) {
        super(builder.getProcessId(),builder.getProcessExecutionType(),builder.getExecutionCount(),builder.isMultiCasting(),builder.getDataSetConfig());
    }


    @Override
    public ProcessOutput execute() {
        return null;
    }
}

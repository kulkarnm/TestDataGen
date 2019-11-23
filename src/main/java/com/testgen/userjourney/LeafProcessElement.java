package com.testgen.userjourney;

import java.util.ArrayList;
import java.util.List;

//Composite Child of Assembly Component - Composite Design Pattern
public class LeafProcessElement extends AbstractProcessElement {

    public LeafProcessElement(ProcessElementBuilder builder) {
        super(builder.getProcessId(),builder.getProcessExecutionType(),builder.getExecutionCount(),builder.isMultiCasting(),builder.getDataSetConfig());
    }

    @Override
    public void addToProcessElements(ProcessElement processElement) {
        //do nothing;
    }

    @Override
    public ProcessOutput execute() {
        return null;
    }


}

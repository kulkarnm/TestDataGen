package com.testgen;

import com.testgen.exceptions.NoDataSetDefinitionForProcessException;
import com.testgen.userjourney.config.process.ProcessElementConfigBuilder;

public class Test {
    public static void main(String[] args) throws NoDataSetDefinitionForProcessException {
        ProcessElementConfigBuilder.buildTestRequests(null);
        System.out.println("hello");
    }

} 

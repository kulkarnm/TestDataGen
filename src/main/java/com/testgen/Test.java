package com.testgen;

import com.testgen.userjourney.config.process.ProcessElementConfig;
import com.testgen.userjourney.config.process.ProcessElementConfigBuilder;

public class Test {
    public static void main(String[] args) {
        ProcessElementConfigBuilder builder = new ProcessElementConfigBuilder();
        ProcessElementConfig processElementConfig = builder.build();
        System.out.println("hello");
    }

} 

package com.testgen.userjourney.config.process;


import com.testgen.userjourney.config.dataset.RequestConfig;

//Composite Child of Assembly Component - Composite Design Pattern
public class CompositeProcessConfig extends AbstractProcessElementConfig {

    public CompositeProcessConfig(ProcessElementConfigBuilder builder) {
      /*  RequestConfigBuilder requestConfigBuilder = builder.getRequestConfigBuilder();
        RequestConfig requestConfig = null;
        if( null!= requestConfigBuilder){
            requestConfig = requestConfigBuilder.buildRequestConfig();
        }*/
        super(builder.getProcessId(),builder.getProcessExecutionType(),builder.getExecutionCount(),builder.isMultiCasting(),null);
    }
}

package com.testgen.userjourney.genetrators;

import com.testgen.userjourney.config.process.RequestParamBuilder;

public class InternalProcessOutputGenerator {

    private DataGenerator dataGenerator;
    private RequestParamBuilder requestParamBuilder;

    public Object getInternalProcessOutput(DataGenerator dataGenerator, RequestParamBuilder requestParamBuilder){
        this.dataGenerator = dataGenerator;
        this.requestParamBuilder = requestParamBuilder;

        if(null != requestParamBuilder.getStartingWithStr()){
            return dataGenerator.getValueFromInternalProcessOutput(requestParamBuilder.getStartingWithStr());
        }
        if (null != requestParamBuilder.getPermissibleValueRange()){
            return dataGenerator.getRandomValueWithinPermissibleRange(requestParamBuilder.getPermissibleValueRange());
        }
        if (null != requestParamBuilder.getInterRelation()){
            String s[] = requestParamBuilder.getInterRelation().split("\\*");
            return dataGenerator.getDerivedValue(s[0],s[1]);
        }
        return null;
    }

}

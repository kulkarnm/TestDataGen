package com.testgen.userjourney.generators;

import com.testgen.userjourney.config.dataset.RequestParam;

public class InternalProcessOutputGenerator {

    private DataGenerator dataGenerator;
    private RequestParam requestParam;

    public Object getInternalProcessOutput(DataGenerator dataGenerator, RequestParam requestParam){
        this.dataGenerator = dataGenerator;
        this.requestParam = requestParam;

        if(null != requestParam.getStartingWithStr()){
            String s[] = requestParam.getStartingWithStr().split("#");
            return dataGenerator.getValueFromInternalProcessOutput(s[0], s[1]);
        }
        if (null != requestParam.getPermissibleValueRange()){
            return dataGenerator.getRandomValueWithinPermissibleRange(requestParam.getPermissibleValueRange());
        }
        if (null != requestParam.getInterRelation()){
            String s[] = requestParam.getInterRelation().split("\\*");
            return dataGenerator.getDerivedValue(s[0],s[1]);
        }
        return null;
    }

}

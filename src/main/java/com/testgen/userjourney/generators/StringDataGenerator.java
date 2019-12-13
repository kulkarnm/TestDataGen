package com.testgen.userjourney.generators;

import com.mifmif.common.regex.Generex;
import com.testgen.userjourney.cache.Cache;

public class StringDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return startingWithStr;
    }

    @Override
    public Object getValueFromInternalProcessOutput(String processName, String paramName) {
        return Cache.getFromCache(processName, paramName, "Request");

    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String regexPattern) {
        Generex generex = new Generex(regexPattern);
        return generex.random();
    }

    @Override
    public Object getDerivedValue(String factor, String value) {
        return null;
    }
}

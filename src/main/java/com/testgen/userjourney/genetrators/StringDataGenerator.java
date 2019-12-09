package com.testgen.userjourney.genetrators;

import com.mifmif.common.regex.Generex;

public class StringDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return startingWithStr;
    }

    @Override
    public Object getValueFromInternalProcessOutput(String startingWithStr) {
        return null;
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

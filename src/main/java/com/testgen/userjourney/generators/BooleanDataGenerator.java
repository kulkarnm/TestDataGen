package com.testgen.userjourney.generators;

public class BooleanDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return Boolean.parseBoolean(startingWithStr);
    }

    @Override
    public Object getValueFromInternalProcessOutput(String processName, String paramName) {
        return null;
    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String range) {
        return null;
    }

    @Override
    public Object getDerivedValue(String factor, String value) {
        return null;
    }
}

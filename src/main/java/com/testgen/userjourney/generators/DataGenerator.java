package com.testgen.userjourney.generators;

public interface DataGenerator {
    Object generateExternalConstValue(String startingWithStr);

    Object getValueFromInternalProcessOutput(String processName, String paramName);

    Object getRandomValueWithinPermissibleRange(String range);

    Object getDerivedValue(String factor, String value);
}

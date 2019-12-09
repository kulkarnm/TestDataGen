package com.testgen.userjourney.genetrators;

public interface DataGenerator {
    Object generateExternalConstValue(String startingWithStr);

    Object getValueFromInternalProcessOutput(String startingWithStr);

    Object getRandomValueWithinPermissibleRange(String range);

    Object getDerivedValue(String factor, String value);
}

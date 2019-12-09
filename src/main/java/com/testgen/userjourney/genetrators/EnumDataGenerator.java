package com.testgen.userjourney.genetrators;

import java.util.Arrays;
import java.util.Random;

public class EnumDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return startingWithStr;
    }

    @Override
    public Object getValueFromInternalProcessOutput(String startingWithStr) {
        return null;
    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String range) {
        return Arrays.asList(range.split("\\|")).get(new Random().nextInt(range.split("\\|").length));
    }

    @Override
    public Object getDerivedValue(String factor, String value) {
        return null;
    }
}

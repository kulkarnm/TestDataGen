package com.testgen.userjourney.generators;

import java.util.Random;

public class IntegerDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return Integer.parseInt(startingWithStr);
    }

    @Override
    public Object getValueFromInternalProcessOutput(String startingWithStr, String paramName) {
        return null;
    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String range) {
        String r[] = range.split("-");
        int minPermissibleValue = Integer.parseInt(r[0]), maxPermissibleValue = Integer.parseInt(r[1]);
        if (minPermissibleValue >= maxPermissibleValue){
            throw new IllegalArgumentException("Min value in Permissible Value range must be less than Max Value");
        }
        Random random = new Random();
        return random.ints(minPermissibleValue, maxPermissibleValue).findAny().getAsInt();
    }

    public Object getDerivedValue(String factor, String value){
        return Integer.parseInt(value) * Float.parseFloat(factor);
    }

}

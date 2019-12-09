package com.testgen.userjourney.genetrators;

import java.util.Random;

public class FloatDataGenerator implements DataGenerator {
    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return Float.parseFloat(startingWithStr);
    }

    @Override
    public Object getValueFromInternalProcessOutput(String startingWithStr) {
        return null;
    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String range) {
        String r[] = range.split("-");
        float minPermissibleValue = Float.parseFloat(r[0]), maxPermissibleValue = Float.parseFloat(r[1]);
        if (minPermissibleValue >= maxPermissibleValue){
            throw new IllegalArgumentException("Min value in Permissible Value range must be less than Max Value");
        }
        Random random = new Random();
        return minPermissibleValue + random.nextFloat() * (maxPermissibleValue - minPermissibleValue);
    }

    @Override
    public Object getDerivedValue(String factor, String value) {
        return Float.parseFloat(factor) * Float.parseFloat(value);
    }

    public Object getDerivedValue(String relation){
        String[] s = relation.split("\\*");
        float factor = Float.parseFloat(s[0]);
        float value = Integer.parseInt(s[1]);
        return factor*value;
    }
}

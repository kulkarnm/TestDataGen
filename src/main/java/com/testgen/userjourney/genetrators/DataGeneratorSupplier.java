package com.testgen.userjourney.genetrators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DataGeneratorSupplier {

    private static final Map<String, Supplier<DataGenerator>> DATAGENERATOR_SUPPLIER;

    static {
        final Map<String, Supplier<DataGenerator>> dataGenerators = new HashMap<>();
        dataGenerators.put("string", StringDataGenerator::new);
        dataGenerators.put("int", IntegerDataGenerator::new);
        dataGenerators.put("boolean", BooleanDataGenerator::new);
        dataGenerators.put("enum", EnumDataGenerator::new);
        dataGenerators.put("date", DateDataGenerator::new);
        dataGenerators.put("float", FloatDataGenerator::new);

        DATAGENERATOR_SUPPLIER = Collections.unmodifiableMap(dataGenerators);
    }

    public DataGenerator supplyDataGenerator(String dataType){
        Supplier<DataGenerator> dataGeneratorSupplier = DATAGENERATOR_SUPPLIER.get(dataType);

        if (null == dataGeneratorSupplier)
            throw new IllegalArgumentException("Invalid Data Type - " + dataType);
        return dataGeneratorSupplier.get();
    }

}

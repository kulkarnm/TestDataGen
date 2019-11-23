package com.testgen.userjourney.generators;

public class StringDataGenerator {

    public String generateStringData(String permissibleValueRange){
        String regex = "[ab]{4,6}c"; Xeger generator = new Xeger(regex); String result = generator.generate(); assert result.matches(regex);
    }
} 

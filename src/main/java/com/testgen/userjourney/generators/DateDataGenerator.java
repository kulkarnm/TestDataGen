package com.testgen.userjourney.generators;

import com.testgen.externalutil.DateUtil;
import com.testgen.userjourney.cache.Cache;

public class DateDataGenerator implements DataGenerator {

    private DateUtil dateUtil;

    public DateDataGenerator() { this.dateUtil = new DateUtil();}

    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return null;
    }

    @Override
    public Object getValueFromInternalProcessOutput(String processName, String paramName) {
        return Cache.getFromCache(processName, paramName,"Request" );
    }

    @Override
    public Object getRandomValueWithinPermissibleRange(String range) {
        return null;
    }

    @Override
    public Object getDerivedValue(String factor, String value) {
        return null;
    }

    public String getDateValue(String paramName){
        if ("businessAccountStartDateFormatted".equals(paramName))
            return dateUtil.formattedStartOfYearString();
        if ("businessAccountEndDateFormatted".equals(paramName))
            return dateUtil.formattedEndOfYearString();
        return null;
    }
}

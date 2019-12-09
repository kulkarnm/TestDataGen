package com.testgen.userjourney.genetrators;

import com.testgen.externalutil.DateUtil;

public class DateDataGenerator implements DataGenerator {

    private DateUtil dateUtil;

    public DateDataGenerator() { this.dateUtil = new DateUtil();}

    @Override
    public Object generateExternalConstValue(String startingWithStr) {
        return null;
    }

    @Override
    public Object getValueFromInternalProcessOutput(String startingWithStr) {
        if ("#(businessAccountStartDateFormatted)".equals(startingWithStr))
            return dateUtil.formattedStartOfYearString();
        if ("#(businessAccountEndDateFormatted)".equals(startingWithStr))
            return dateUtil.formattedEndOfYearString();
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

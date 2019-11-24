package com.testgen.externalutil;

import org.joda.time.LocalDate;

public class DateUtil {

    public String unformattedStartOfYearString(){
        LocalDate targetDate = new LocalDate();
        String dd = "01";
        String mm = "01";
        int yyyy = targetDate.getYear();
        return "["+yyyy+","+ mm + "," + dd +"]" ;
    }

    public String formattedStartOfYearString(){
        LocalDate targetDate = new LocalDate();
        String dd = "01";
        String mm = "01";
        int yyyy = targetDate.getYear();
        return yyyy + "-" + mm + "-" + dd ;
    }

    public LocalDate unformattedStartOfYearDate(){
        LocalDate targetDate = new LocalDate();
        int dd = 1;
        int mm = 1;
        int yyyy = targetDate.getYear();
        return new LocalDate(yyyy,mm,dd) ;
    }

    public String unformattedEndOfYearString(){
        LocalDate targetDate = new LocalDate();
        int dd = 31;
        int mm = 12;
        int yyyy = targetDate.getYear();
        return "["+yyyy+","+ mm + "," + dd +"]" ;
    }
    public String formattedEndOfYearString(){
        LocalDate targetDate = new LocalDate();
        int dd = 31;
        int mm = 12;
        int yyyy = targetDate.getYear();
        return yyyy +"-"+ mm + "-" + dd ;
    }
    public LocalDate unformattedEndOfYearDate(){
        LocalDate targetDate = new LocalDate();
        int yyyy = targetDate.getYear();
        return new LocalDate(yyyy,12,31);
    }

    public LocalDate daysAfterDate(LocalDate startDate,int noOfDaysAfter){
        LocalDate newDate = startDate.plusDays(noOfDaysAfter);
        return newDate;
    }

    public String unformattedDateString(LocalDate targetDate){
        int dd= targetDate.getDayOfMonth();
        int mm = targetDate.getMonthOfYear();
        int yyyy = targetDate.getYear();
        return "["+yyyy+","+ mm  + "," + dd +"]" ;
    }

    public String formattedDateString(LocalDate targetDate){
        int dd= targetDate.getDayOfMonth();
        int mm = targetDate.getMonthOfYear();
        int yyyy = targetDate.getYear();
        String mmString = "" + mm;
        if(mm <= 9){
            mmString = "0" + mm ;
        }
        String ddString = "" + dd;
        if(dd <= 9){
            ddString = "0" + dd;
        }
        return "["+yyyy+","+ mmString  + "," + ddString +"]" ;
    }

    public String businessYear(){
        LocalDate targetDate = new LocalDate();
        return ""+ targetDate.getYear();
    }
    public LocalDate firstDayOfAMonth(int month,int year){
        LocalDate targetDate = new LocalDate(year,month,1);
        return targetDate;
    }

    public LocalDate lastDayOfAMonth(int month,int year){
        LocalDate targetDate = new LocalDate(year,month,1);
        LocalDate endOfMonth = targetDate.dayOfMonth().withMaximumValue();
        return endOfMonth;
    }


} 

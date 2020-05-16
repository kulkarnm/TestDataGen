package com.testgen.userjourney.cache;

import com.testgen.userjourney.generators.DateDataGenerator;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    public static Map<String , List<CacheSegment>> cache = new HashMap<>();

    public static void addToCache(String key, CacheSegment param){
        if (cache.containsKey(key)){
            cache.get(key).add(param);
        }else {
            List<CacheSegment> cacheSegmentsInCache = new ArrayList<>();
            cacheSegmentsInCache.add(param);
            cache.put(key, cacheSegmentsInCache);
        }
    }

    public static String getFromCache(String key, String paramName, String paramType){
        if (!cache.containsKey(key))
            throw new IllegalArgumentException("Key not present in the Cache");
        List<CacheSegment> cacheSegmentsInCache = cache.get(key);
        CacheSegment cacheSegment1 = cacheSegmentsInCache.stream()
                                        .filter( e-> e.getParamName().equals(paramName) && e.getParamType().equals(paramType))
                                        .findFirst()
                                        .orElseThrow(IllegalArgumentException::new);
        System.out.println("Cache value - " + cacheSegment1.getParamValue() );
        return cacheSegment1.getParamValue();
    }

    public static void populateGlobalCache(){
        DateDataGenerator dateDataGenerator = new DateDataGenerator();
        CacheSegment cacheSegment = new CacheSegment("businessAccountStartDateFormatted", "Request", dateDataGenerator.getDateValue("businessAccountStartDateFormatted"));
        Cache.addToCache("GlobalCache", cacheSegment);
        cacheSegment  =new CacheSegment("businessAccountEndDateFormatted", "Request", dateDataGenerator.getDateValue("businessAccountEndDateFormatted"));
        Cache.addToCache("GlobalCache",cacheSegment);
    }
}

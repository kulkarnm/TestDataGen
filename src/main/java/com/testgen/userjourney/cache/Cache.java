package com.testgen.userjourney.cache;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    public static Map<String , List<CacheSegment>> cache = new HashMap<>();

  /*  public Cache(){
        cache = new HashMap<>();
    }*/

    public static void addToCache(String key, CacheSegment param){
        if (cache.containsKey(key)){
            cache.get(key).add(param);
        }else {
            List<CacheSegment> cacheSegmentsInCache = new ArrayList<CacheSegment>();
            cacheSegmentsInCache.add(param);
            cache.put(key, cacheSegmentsInCache);
        }
    }

    public static String getFromCache(String key, String paramName, String paramType){
        if (!cache.containsKey(key))
            throw new IllegalArgumentException("Key not present in the Cache");
        List<CacheSegment> cacheSegmentsInCache = cache.get(key);
        //CacheSegment cacheSegment = cacheSegmentsInCache.stream().filter(e -> e.getParamType().equals(paramType)).findFirst().orElseThrow(IllegalArgumentException::new);
        CacheSegment cacheSegment1 = cacheSegmentsInCache.stream().filter( e-> e.getParamName().equals(paramName) && e.getParamType().equals(paramType)).findFirst().orElseThrow(IllegalArgumentException::new);
        System.out.println("Cache value - " + cacheSegment1.getParamValue() );
        return null;
    }

    public static void displayCache(){
        cache.keySet().forEach(
                e -> cache.get(e).stream().forEach(System.out::println)
        );
    }
}

package com.testgen.userjourney.config.process;

import com.testgen.userjourney.cache.Cache;
import com.testgen.userjourney.cache.CacheSegment;
import com.testgen.userjourney.genetrators.DataGenerator;
import com.testgen.userjourney.genetrators.DataGeneratorSupplier;
import com.testgen.userjourney.genetrators.InternalProcessOutputGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class RequestBuilder {

    private JSONObject requestJson;
    private DataGenerator dataGenerator;
    private String processName;
    private CacheSegment cacheSegmentToBeAddedInCache;

    public RequestBuilder() {
        requestJson = new JSONObject();
    }

    public RequestBuilder buildRequest(RequestConfigBuilder requestConfigBuilder) {
        this.processName = requestConfigBuilder.getRequestId();
        requestConfigBuilder.getRequestParamBuilders().stream().forEach(e -> addToJsonRequest(e));
        writeRequestToJsonFile(requestJson, requestConfigBuilder.getRequestId());
        return this;
    }

    private void writeRequestToJsonFile(JSONObject requestJson, String requestId) {
        try (FileWriter file = new FileWriter("src/main/resources/json/" + requestId + ".json")) {
            file.write(requestJson.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToJsonRequest(RequestParamBuilder requestParam) {
        CacheSegment cacheSegment = new CacheSegment();
        if ("[".equals(requestParam.getParamType().substring(0, 1))) {
            JSONArray jsonArray = new JSONArray();
            requestParam.getStartingWith().stream().forEach(
                    e -> jsonArray.put(generateRequestForTypeObject(e))
            );
            requestJson.put(requestParam.getParamName(), jsonArray);
            this.cacheSegmentToBeAddedInCache = cacheSegment.createCacheSegmentFromJSON(requestParam.getParamName(), "Request", jsonArray.toString());
        } else {
            Object jsonValue = getRequestParamValue(requestParam);
            requestJson.put(requestParam.getParamName(), jsonValue);
            this.cacheSegmentToBeAddedInCache = cacheSegment.createCacheSegmentFromJSON(requestParam.getParamName(), "Request", jsonValue.toString());
        }
        Cache.addToCache(processName, this.cacheSegmentToBeAddedInCache);
    }

    private Object getRequestParamValue(RequestParamBuilder requestParam) {

        DataGeneratorSupplier dataGeneratorSupplier = new DataGeneratorSupplier();
        this.dataGenerator = dataGeneratorSupplier.supplyDataGenerator(requestParam.getParamType());

        switch (requestParam.getParamSource()) {
            case EXTERNAL_CONST:
                return dataGenerator.generateExternalConstValue(requestParam.getStartingWithStr());
            case INTERNAL_PROCESS_OUTPUT:
                InternalProcessOutputGenerator internalProcessOutputGenerator = new InternalProcessOutputGenerator();
                return internalProcessOutputGenerator.getInternalProcessOutput(dataGenerator, requestParam);
            case EXTERNAL:
                return dataGenerator.getRandomValueWithinPermissibleRange(requestParam.getPermissibleValueRange());
            default:
                throw new IllegalArgumentException("Param Source supplied is not in the defined list of sources");
        }
    }

    private JSONObject generateRequestForTypeObject(RequestParamBuilder requestParam) {
        JSONObject jsonObject = new JSONObject();
        if (requestParam.getParamType().equals("object")) {
            requestParam.getStartingWith().stream().forEach(
                    e -> jsonObject.put(e.getParamName(), getRequestParamValue(e))
            );
        } else {
            jsonObject.put(requestParam.getParamName(), getRequestParamValue(requestParam));
        }
        return jsonObject;
    }

}

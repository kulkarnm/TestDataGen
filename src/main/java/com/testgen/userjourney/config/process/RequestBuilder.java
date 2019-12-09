package com.testgen.userjourney.config.process;

import com.testgen.userjourney.genetrators.DataGenerator;
import com.testgen.userjourney.genetrators.DataGeneratorSupplier;
import com.testgen.userjourney.genetrators.InternalProcessOutputGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.management.MBeanRegistration;
import java.io.FileWriter;
import java.io.IOException;

public class RequestBuilder {

    private JSONObject requestJson;
    private DataGenerator dataGenerator;

    public RequestBuilder() { requestJson = new JSONObject(); };

    public RequestBuilder buildRequest(RequestConfigBuilder requestConfigBuilder){
        requestConfigBuilder.getRequestParamBuilders().stream().forEach(e -> addToJsonRequest(e));
        System.out.println("Request ---");
        System.out.println(requestJson);
        writeRequestToJsonFile(requestJson, requestConfigBuilder.getRequestId());
        return this;
    }

    private void writeRequestToJsonFile(JSONObject requestJson, String requestId) {
        try (FileWriter file = new FileWriter("src/main/resources/json/" + requestId + ".json")){
            file.write(requestJson.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToJsonRequest(RequestParamBuilder requestParam) {
        System.out.println("name - " + requestParam.getParamName());

        //if("[object]".equals(requestParam.getParamType()) || "[enum]".equals(requestParam.getParamType())){
        if("[".equals(requestParam.getParamType().substring(0,1))){
            JSONArray jsonArray = new JSONArray();
            requestParam.getStartingWith().stream().forEach(
                    e -> jsonArray.put(generateRequestForTypeObject(e))
            );
            requestJson.put(requestParam.getParamName(), jsonArray);
        } else {
            Object jsonValue = getRequestParamValue(requestParam);
            requestJson.put(requestParam.getParamName(), jsonValue);
        }
    }

    private Object getRequestParamValue(RequestParamBuilder requestParam) {
        Object responseJsonValue= null;
        if (!"[object]".equals(requestParam.getParamType())){
            DataGeneratorSupplier dataGeneratorSupplier = new DataGeneratorSupplier();
            this.dataGenerator = dataGeneratorSupplier.supplyDataGenerator(requestParam.getParamType());

            switch (requestParam.getParamSource()){
                case EXTERNAL_CONST:
                    responseJsonValue = dataGenerator.generateExternalConstValue(requestParam.getStartingWithStr());
                    break;
                case INTERNAL_PROCESS_OUTPUT:
                    InternalProcessOutputGenerator internalProcessOutputGenerator = new InternalProcessOutputGenerator();
                    responseJsonValue = internalProcessOutputGenerator.getInternalProcessOutput(dataGenerator, requestParam);
                    break;
                case EXTERNAL:
                    responseJsonValue = dataGenerator.getRandomValueWithinPermissibleRange(requestParam.getPermissibleValueRange());
                    break;
            }

        }
        return responseJsonValue;
    }

    private JSONObject generateRequestForTypeObject(RequestParamBuilder requestParam) {
        JSONObject jsonObject = new JSONObject();
        if (requestParam.getParamType().equals("object")){
            requestParam.getStartingWith().stream().forEach(
                    e -> jsonObject.put(e.getParamName(), getRequestParamValue(e))
            );
        }else {
            jsonObject.put(requestParam.getParamName(), getRequestParamValue(requestParam));
        }

        return jsonObject;
    }

}

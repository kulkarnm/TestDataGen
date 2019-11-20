package com.testgen.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class JsonParser {
    private static String[] SCHEMA_FILES =
            new String[] {
                    "process/root-process-config.json",
            };
    public void parse(){
        try {
            String genreJson="";
            for(String schemaFileName: SCHEMA_FILES) {
                URL url = Resources.getResource(schemaFileName);
                genreJson = Resources.toString(url, Charsets.UTF_8);
                System.out.println("json:" + genreJson);
            }
            JSONObject json = new JSONObject(genreJson);
            System.out.println(json.toString());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String [] args){
        JsonParser parser = new JsonParser();
        parser.parse();
    }
} 

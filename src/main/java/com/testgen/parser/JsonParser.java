package com.testgen.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class JsonParser {

    public JSONObject parse(String schemaFileName) {
        try {
            String genreJson = "";
            URL url = Resources.getResource(schemaFileName);
            genreJson = Resources.toString(url, Charsets.UTF_8);
            //System.out.println("json:" + genreJson);
            JSONObject json = new JSONObject(genreJson);
            //System.out.println(json.toString());
            //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


} 

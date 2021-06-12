package com.neivor.vertx.Util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UtilJson {
    public static String objetoAJsonString(Object jsonElement) {

        Gson gson = new Gson();
        return gson.toJson(jsonElement);
    }
    public static JsonObject stringaJsonString(String cadena){
        JsonParser  parser = new JsonParser();
       JsonObject jsonElement = (JsonObject)parser.parse(cadena);
       return jsonElement;
    }
}

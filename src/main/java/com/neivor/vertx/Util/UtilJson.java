package com.neivor.vertx.Util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**

 * Esta clase define las diferentes metodos para la manipulacion de objetos java y json

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */
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

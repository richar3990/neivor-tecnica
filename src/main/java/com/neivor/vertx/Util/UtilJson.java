package com.neivor.vertx.Util;

import com.google.gson.Gson;

public class UtilJson {
    public static String objetoAJsonString(Object jsonElement) {

        Gson gson = new Gson();

        return gson.toJson(jsonElement);
    }
}

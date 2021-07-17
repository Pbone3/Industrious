package dev.industrious.utils;

import com.google.gson.JsonObject;

public class JsonUtils {
    public static void SwapJsonProperty(JsonObject jObj, String property, Number value) {
        jObj.remove(property);
        jObj.addProperty(property, value);
    }

    public static void SwapJsonProperty(JsonObject jObj, String property, String value) {
        jObj.remove(property);
        jObj.addProperty(property, value);
    }

    public static void SwapJsonProperty(JsonObject jObj, String property, Boolean value) {
        jObj.remove(property);
        jObj.addProperty(property, value);
    }

    public static void SwapJsonProperty(JsonObject jObj, String property, Character value) {
        jObj.remove(property);
        jObj.addProperty(property, value);
    }
}

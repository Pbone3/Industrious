package dev.industrious.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

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

    public static JsonObject DeepCopy(JsonObject other) {
        JsonObject jObj = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : other.entrySet()) {
            jObj.add(entry.getKey(), entry.getValue());
        }

        return jObj;
    }
}

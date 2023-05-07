package kor.toxicity.topping.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public final class JsonUtil {
    private static final Gson GSON = new Gson();
    public static String toString(JsonElement element) {
        return GSON.toJson(element);
    }
}

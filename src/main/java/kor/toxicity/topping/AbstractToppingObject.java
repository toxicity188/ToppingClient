package kor.toxicity.topping;

import com.google.gson.JsonObject;
import kor.toxicity.topping.util.JsonUtil;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractToppingObject {
    @NotNull
    public abstract JsonObject getAsJsonObject();

    @Override
    public String toString() {
        return JsonUtil.toString(getAsJsonObject());
    }
}

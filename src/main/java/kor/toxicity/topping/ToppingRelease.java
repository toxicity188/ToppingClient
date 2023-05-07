package kor.toxicity.topping;

import com.google.gson.JsonObject;
import kor.toxicity.topping.util.DateUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class ToppingRelease extends AbstractToppingObject {
    private final long id, size;
    @NotNull
    private final ProductStatus status;
    @NotNull
    private final String file, name;
    @NotNull
    private final String[] description;
    @NotNull
    private final LocalDateTime createTime, updateTime;

    public ToppingRelease(JsonObject object) {
        id = object.getAsJsonPrimitive("id").getAsLong();
        size = object.getAsJsonPrimitive("size").getAsLong();
        file = object.getAsJsonPrimitive("file").getAsString();
        name = object.getAsJsonPrimitive("name").getAsString();
        description = object.getAsJsonPrimitive("description").getAsString().split("\n");
        status = ProductStatus.values()[object.getAsJsonPrimitive("status").getAsInt()];
        createTime = DateUtil.parse(object.getAsJsonPrimitive("createdAt").getAsString());
        updateTime = DateUtil.parse(object.getAsJsonPrimitive("updatedAt").getAsString());
    }

    @Override
    @NotNull
    public JsonObject getAsJsonObject() {

        JsonObject object = new JsonObject();
        object.addProperty("id",id);
        object.addProperty("size",size);
        object.addProperty("file",file);
        object.addProperty("name",name);

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String s : description) {
            builder.append(s);
            if (++i < description.length) builder.append("\n");
        }
        object.addProperty("description",builder.toString());
        object.addProperty("status",status.ordinal());
        object.addProperty("createdAt",createTime + "Z");
        object.addProperty("updatedAt", updateTime + "Z");
        return object;
    }
}

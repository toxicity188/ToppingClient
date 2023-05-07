package kor.toxicity.topping;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kor.toxicity.topping.util.DateUtil;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class ToppingProduct extends AbstractToppingObject {
    private final long id;
    private final double price;
    @NotNull
    private final String name;
    @NotNull
    private final String[] description;
    @NotNull
    private final ProductStatus status;
    @NotNull
    private final LocalDateTime createTime, updateTime;

    @NotNull
    private final ToppingRelease[] releases;

    ToppingProduct(JsonObject object) {
        id = object.getAsJsonPrimitive("id").getAsLong();
        price = object.getAsJsonPrimitive("price").getAsDouble();
        name = object.getAsJsonPrimitive("name").getAsString();

        description = object.getAsJsonPrimitive("description").getAsString().split("\n");
        status = ProductStatus.values()[object.getAsJsonPrimitive("status").getAsInt()];
        createTime = DateUtil.parse(object.getAsJsonPrimitive("createdAt").getAsString());
        updateTime = DateUtil.parse(object.getAsJsonPrimitive("updatedAt").getAsString());

        JsonArray array = object.getAsJsonArray("releases");

        releases = new ToppingRelease[array.size()];
        int i = 0;
        for (JsonElement element : array) {
            releases[i++] = new ToppingRelease(element.getAsJsonObject());
        }
    }

    @NotNull
    public ToppingRelease getLatestRelease() {
        return releases[0];
    }



    @Override
    @NotNull
    public JsonObject getAsJsonObject() {

        JsonObject object = new JsonObject();
        object.addProperty("id",id);
        object.addProperty("price",price);
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

        JsonArray array1 = new JsonArray();
        for (ToppingRelease release : releases) {
            array1.add(release.getAsJsonObject());
        }
        object.add("releases",array1);
        return object;
    }
}
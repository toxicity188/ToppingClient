package kor.toxicity.topping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Setter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public final class ToppingClient {
    private ToppingClient() {
        throw new RuntimeException();
    }
    @Nullable
    public static ToppingProduct getProductInfo(long userId, long productId) {
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(new HttpGet("https://topping-market.com/api/public/products/" + userId + "/" + productId))) {
            JsonObject object = JsonParser.parseString(new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine()).getAsJsonObject().getAsJsonObject("product");
            return new ToppingProduct(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Nullable
    public static ToppingProduct getProductInfo(JsonObject object) {
        try {
            return new ToppingProduct(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Setter
    private static AsyncExecutor executor;

    public static void getAsyncProductInfo(long userid, long productId,@Nullable Consumer<@NotNull ToppingProduct> thenAccent) {
        if (executor == null) return;
        executor.asyncRequest(userid,productId, thenAccent);
    }
}

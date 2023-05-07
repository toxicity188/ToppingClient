package kor.toxicity.topping;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@FunctionalInterface
public interface AsyncExecutor {
    void asyncRequest(long userId, long productId, @Nullable Consumer<@NotNull ToppingProduct> accept);
}

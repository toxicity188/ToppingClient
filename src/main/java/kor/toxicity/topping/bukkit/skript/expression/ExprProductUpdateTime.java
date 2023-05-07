package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingProduct;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprProductUpdateTime extends SimpleExpression<String> {

    private Expression<ToppingProduct> productExpression;

    @Override
    protected String[] get(@NotNull Event e) {
        ToppingProduct product = productExpression.getSingle(e);
        if (product != null) return new String[] {
                product.getUpdateTime().toString()
        };
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "get update time from product";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        productExpression = (Expression<ToppingProduct>) exprs[0];
        return true;
    }
}

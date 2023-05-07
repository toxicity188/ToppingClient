package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingProduct;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprProductID extends SimpleExpression<Long> {

    private Expression<ToppingProduct> productExpression;

    @Override
    protected Long[] get(@NotNull Event e) {
        ToppingProduct product = productExpression.getSingle(e);
        if (product != null) return new Long[] {
                product.getId()
        };
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "get id from product";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        productExpression = (Expression<ToppingProduct>) exprs[0];
        return true;
    }
}

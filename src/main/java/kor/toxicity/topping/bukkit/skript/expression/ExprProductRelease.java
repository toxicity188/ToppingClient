package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingProduct;
import kor.toxicity.topping.ToppingRelease;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprProductRelease extends SimpleExpression<ToppingRelease> {

    private Expression<ToppingProduct> productExpression;

    @Override
    protected ToppingRelease[] get(@NotNull Event e) {
        ToppingProduct product = productExpression.getSingle(e);
        if (product != null) return product.getReleases();
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends ToppingRelease> getReturnType() {
        return ToppingRelease.class;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "get releases from product";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        productExpression = (Expression<ToppingProduct>) exprs[0];
        return true;
    }
}

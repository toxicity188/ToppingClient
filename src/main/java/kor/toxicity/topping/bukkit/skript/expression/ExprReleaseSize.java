package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingRelease;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprReleaseSize extends SimpleExpression<Long> {

    private Expression<ToppingRelease> productExpression;

    @Override
    protected Long[] get(@NotNull Event e) {
        ToppingRelease release = productExpression.getSingle(e);
        if (release != null) return new Long[] {
                release.getSize()
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
        return "get size from release";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        productExpression = (Expression<ToppingRelease>) exprs[0];
        return true;
    }
}

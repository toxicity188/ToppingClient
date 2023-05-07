package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingRelease;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprReleaseDescription extends SimpleExpression<String> {

    private Expression<ToppingRelease> productExpression;

    @Override
    protected String[] get(@NotNull Event e) {
        ToppingRelease release = productExpression.getSingle(e);
        if (release != null) return release.getDescription();
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "get description name from release";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        productExpression = (Expression<ToppingRelease>) exprs[0];
        return true;
    }
}

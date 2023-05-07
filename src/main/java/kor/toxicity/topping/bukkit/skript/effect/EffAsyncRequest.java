package kor.toxicity.topping.bukkit.skript.effect;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingClient;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class EffAsyncRequest extends Effect {

    private Expression<Integer> integerExpression1, integerExpression2;

    @Override
    protected void execute(@NotNull Event e) {
        Integer i1 = integerExpression1.getSingle(e);
        Integer i2 = integerExpression2.getSingle(e);
        if (i1 == null || i2 == null) return;
        ToppingClient.getAsyncProductInfo(i1,i2,null);
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "Async Topping request effect: " + integerExpression1.toString(e,debug) + "," + integerExpression2.toString(e,debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        integerExpression1 = (Expression<Integer>) exprs[0];
        integerExpression2 = (Expression<Integer>) exprs[1];
        return true;
    }
}

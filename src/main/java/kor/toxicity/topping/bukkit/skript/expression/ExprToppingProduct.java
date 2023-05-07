package kor.toxicity.topping.bukkit.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.toxicity.topping.ToppingClient;
import kor.toxicity.topping.ToppingProduct;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class ExprToppingProduct extends SimpleExpression<ToppingProduct> {

    private Expression<Integer> integerExpression1;
    private Expression<Integer> integerExpression2;

    @Override
    protected ToppingProduct[] get(@NotNull Event e) {
        Integer i1 = integerExpression1.getSingle(e);
        Integer i2 = integerExpression2.getSingle(e);
        if (i1 == null || i2 == null) return null;
        try {
            return new ToppingProduct[] {
                    ToppingClient.getProductInfo(i1,i2)
            };
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends ToppingProduct> getReturnType() {
        return ToppingProduct.class;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "request from topping: " + integerExpression1.toString(e,debug) + "," + integerExpression2.toString(e,debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        integerExpression1 = (Expression<Integer>) exprs[0];
        integerExpression2 = (Expression<Integer>) exprs[1];
        return true;
    }
}

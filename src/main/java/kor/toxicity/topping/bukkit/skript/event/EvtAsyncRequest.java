package kor.toxicity.topping.bukkit.skript.event;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import kor.toxicity.topping.bukkit.event.ToppingConnectEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public final class EvtAsyncRequest extends SkriptEvent {


    private Literal<String> stringLiteral;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        stringLiteral = (Literal<String>) args[0];
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        if (stringLiteral != null) {
            String name = ((ToppingConnectEvent) e).getProduct().getName();
            return stringLiteral.check(e,name::equals);
        }
        return true;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "Async Topping request event: " + (stringLiteral != null ? stringLiteral.toString(e,debug) : "null");
    }
}

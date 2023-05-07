package kor.toxicity.topping.bukkit.event;

import kor.toxicity.topping.ToppingProduct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@RequiredArgsConstructor
public final class ToppingConnectEvent extends Event {
    @Getter
    private static final HandlerList handlerList = new HandlerList();
    @Getter
    private final ToppingProduct product;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}

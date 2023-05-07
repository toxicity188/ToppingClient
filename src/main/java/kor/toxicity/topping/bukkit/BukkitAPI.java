package kor.toxicity.topping.bukkit;

import kor.toxicity.topping.AsyncExecutor;
import kor.toxicity.topping.ToppingClient;
import kor.toxicity.topping.ToppingProduct;
import kor.toxicity.topping.bukkit.event.ToppingConnectEvent;
import kor.toxicity.topping.bukkit.skript.SkriptRegister;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class BukkitAPI extends JavaPlugin implements AsyncExecutor {
    @Override
    public void onEnable() {
        ToppingClient.setExecutor(this);
        getLogger().info("plugin enabled.");
        if (Bukkit.getPluginManager().isPluginEnabled("Skript")) {
            try {
                SkriptRegister.load();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("plugin disabled.");
    }

    @Override
    public void asyncRequest(long userId, long productId, @Nullable Consumer<@NotNull ToppingProduct> accept) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskAsynchronously(this,() -> {
            ToppingProduct product = ToppingClient.getProductInfo(userId,productId);
            if (product != null) {
                if (accept != null) accept.accept(product);
                Bukkit.getScheduler().runTask(this,() -> Bukkit.getPluginManager().callEvent(new ToppingConnectEvent(product)));
            }
        });
    }
}

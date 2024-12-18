package codes.bed.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import org.bukkit.plugin.java.JavaPlugin;


public final class Example extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandDispatcher<CommandSourceStack> dispatcher = DispatcherUtil.getDispatcher(getServer());

        if (dispatcher != null) {
            MyBrigadierCommand.register(dispatcher);
            getLogger().info("Brigadier command registered successfully.");
        } else {
            getLogger().severe("Failed to register Brigadier command.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

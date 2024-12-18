package codes.bed.brigadier;

import org.bukkit.Server;
import com.mojang.brigadier.CommandDispatcher;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

/**
 * Utility class for retrieving the CommandDispatcher from a Minecraft server.
 */
public class DispatcherUtil {

    /**
     * Retrieves the CommandDispatcher from the given Bukkit server.
     *
     * @param server the Bukkit server instance
     * @return the CommandDispatcher for the server, or null if an error occurs
     */
    public static CommandDispatcher<CommandSourceStack> getDispatcher(Server server) {
        try {
            // Get the NMS (net.minecraft.server) server instance from the Bukkit server
            Method getServer = server.getClass().getDeclaredMethod("getServer");
            getServer.setAccessible(true);
            Object nmsServer = getServer.invoke(server);

            Field resourcesField = nmsServer.getClass().getField("az");
            resourcesField.setAccessible(true);
            Object resourcesValue = resourcesField.get(nmsServer);

            Method managersMethod = resourcesValue.getClass().getMethod("b");
            managersMethod.setAccessible(true);
            Object managersResult = managersMethod.invoke(resourcesValue);

            Field commandsField = managersResult.getClass().getField("e");
            commandsField.setAccessible(true);
            Object commandsValue = commandsField.get(managersResult);

            Method dispatcherMethod = commandsValue.getClass().getMethod("a");
            dispatcherMethod.setAccessible(true);
            return (CommandDispatcher<CommandSourceStack>) dispatcherMethod.invoke(commandsValue);

        } catch (Exception e) {
            return null;
        }
    }
}
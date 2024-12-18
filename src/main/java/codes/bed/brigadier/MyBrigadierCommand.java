package codes.bed.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

public class MyBrigadierCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                LiteralArgumentBuilder.<CommandSourceStack>literal("example")
                        .then(
                                RequiredArgumentBuilder.<CommandSourceStack, String>argument("arg", StringArgumentType.string())
                                        .executes(context -> {
                                            String argument = context.getArgument("arg", String.class);
                                            System.out.println("You typed: " + argument);
                                            return 1; // Success code
                                        })
                        )
        );
    }
}

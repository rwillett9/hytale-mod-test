package dev.hytalemodding;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.hytalemodding.commands.ExampleCommand;
import dev.hytalemodding.commands.ReturnToBed;
import dev.hytalemodding.events.ExampleEvent;

import javax.annotation.Nonnull;

public class TestPlugin extends JavaPlugin {

    public TestPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand("Ryguy's Plugin", "INSERT VERSION NUMBER HERE"));
        this.getCommandRegistry().registerCommand(new ReturnToBed());
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }
}
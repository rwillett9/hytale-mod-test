package dev.hytalemodding.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.protocol.Position;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.SpawnUtil;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.spawn.GlobalSpawnProvider;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * This is an example command that will simply print the name of the plugin in chat when used.
 */
public class ReturnToBed extends AbstractPlayerCommand {
    public ReturnToBed() {
        super("imscared", "Teleports the player back to their bed because they're a little scaredy-cat.");
        this.setPermissionGroup(GameMode.Adventure); // Allows the command to be used by anyone, not just OP
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        // teleports the player to their set spawn point
//        Player player = store.getComponent(ref, Player.getComponentType());
        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        UUID uuid = uuidComponent.getUuid();
        GlobalSpawnProvider globalSpawnProvider = (GlobalSpawnProvider) world.getWorldConfig().getSpawnProvider();
        Transform spawnPoint = globalSpawnProvider.getSpawnPoint(world, uuid);
//        playerComponent.getPlayerConfigData().getPerWorldData().containsKey(this.name)
        world.execute(() -> {
            Position spawnPosition = world.
            // fetch the player's spawn point
            Teleport teleport = new Teleport(spawnPoint);

        });
    }
}
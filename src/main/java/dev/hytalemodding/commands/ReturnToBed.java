package dev.hytalemodding.commands;

import com.hypixel.hytale.builtin.teleport.components.TeleportHistory;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.protocol.FormattedMessage;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.HeadRotation;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.spawn.FitToHeightMapSpawnProvider;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * This is an example command that will simply print the name of the plugin in chat when used.
 */
public class ReturnToBed extends AbstractPlayerCommand {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public ReturnToBed() {
        super("imscared", "Teleports the player back to their bed because they're a little scaredy cat.");
        this.setPermissionGroup(GameMode.Adventure); // Allows the command to be used by anyone, not just OP
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        assert transformComponent != null;

        HeadRotation headRotation = store.getComponent(ref, HeadRotation.getComponentType());
        assert headRotation != null;

        Vector3d previousPos = transformComponent.getPosition().clone();
        Vector3f previousHeadRotation = headRotation.getRotation().clone();
        TeleportHistory teleportHistoryComponent = store.getComponent(ref, TeleportHistory.getComponentType());
        teleportHistoryComponent.append(world, previousPos, previousHeadRotation, "Home");

        Transform homeTransform = Player.getRespawnPosition(ref, world.getName(), store);
        Teleport teleport = Teleport.createForPlayer(homeTransform);

        store.addComponent(ref, Teleport.getComponentType(), teleport);
        commandContext.sendMessage(Message.raw("this syntax sucks"));
    }
}
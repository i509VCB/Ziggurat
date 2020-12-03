package me.i509.ziggurat.internal;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.actor.PlayerActor;

/**
 * This is an internal implementation class for ziggurat.
 * You should not be using this at all!
 */
@ApiStatus.Internal
public interface Implementation {
	GameSession getSession(MinecraftServer server) throws IllegalStateException;

	FlagFactories getFlagFactories();

	PlayerActor getPlayerActor(ServerPlayerEntity player);
}

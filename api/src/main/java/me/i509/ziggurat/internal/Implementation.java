package me.i509.ziggurat.internal;

import java.util.UUID;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.actor.PlayerActor;
import me.i509.ziggurat.api.flag.FlagType;

/**
 * This is an internal implementation class for ziggurat.
 * You should not be using this at all!
 */
@ApiStatus.Internal
public interface Implementation {
	GameSession getSession(MinecraftServer server) throws IllegalStateException;

	FlagType.Bool createBoolFlag(Identifier id);

	FlagType.Int getOrCreateIntFlag(Identifier id, int minimumBound, int maximumBound);

	FlagType.Double getOrCreateDoubleFlag(Identifier id, double minimumBound, double maximumBound);

	FlagType.Uuid getOrCreateUuidFlag(Identifier id);

	<E extends Enum<E>> FlagType.Enum<E> getOrCreateEnumFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.RegistryEntry<V> getOrCreateRegistryEntryFlag(Identifier id, Registry<V> registry);

	FlagType.Set<UUID> getOrCreateUuidSetFlag(Identifier id);

	<E extends Enum<E>> FlagType.Set<E> getOrCreateEnumSetFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.Set<V> getOrCreateRegistryEntrySetFlag(Identifier id, Class<V> entryClass, Registry<V> registry);

	PlayerActor getPlayerActor(ServerPlayerEntity player);
}

package me.i509.ziggurat.internal;

import java.util.UUID;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.flag.FlagType;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * This is an internal implementation class for ziggurat.
 * You should not be using this at all!
 */
@ApiStatus.Internal
public interface Implementation {
	GameSession getSession(MinecraftServer server) throws IllegalStateException;

	FlagType.Bool createBoolFlag(Identifier id);

	FlagType.Int createIntFlag(Identifier id, int minimumBound, int maximumBound);

	FlagType.Int createDoubleFlag(Identifier id, double minimumBound, double maximumBound);

	FlagType.Uuid createUuidFlag(Identifier id);

	<E extends Enum<E>> FlagType.Enum<E> createEnumFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.RegistryEntry<V> createRegistryEntryFlag(Identifier id, Registry<V> registry);

	FlagType.Set<UUID> createUuidSetFlag(Identifier id);

	<E extends Enum<E>> FlagType.Set<E> createEnumSetFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.Set<V> createRegistryEntrySet(Identifier id, Class<V> entryClass, Registry<V> registry);
}

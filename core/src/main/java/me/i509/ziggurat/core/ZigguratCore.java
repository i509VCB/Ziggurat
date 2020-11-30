package me.i509.ziggurat.core;

import java.util.UUID;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.flag.FlagType;
import me.i509.ziggurat.internal.Implementation;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ZigguratCore implements Implementation {
	@Override
	public GameSession getSession(MinecraftServer server) throws IllegalStateException {
		return null;
	}

	@Override
	public FlagType.Bool createBoolFlag(Identifier id) {
		return null;
	}

	@Override
	public FlagType.Int createIntFlag(Identifier id, int minimumBound, int maximumBound) {
		return null;
	}

	@Override
	public FlagType.Int createDoubleFlag(Identifier id, double minimumBound, double maximumBound) {
		return null;
	}

	@Override
	public FlagType.Uuid createUuidFlag(Identifier id) {
		return null;
	}

	@Override
	public <E extends Enum<E>> FlagType.Enum<E> createEnumFlag(Identifier id, Class<E> enumClass) {
		return null;
	}

	@Override
	public <V> FlagType.RegistryEntry<V> createRegistryEntryFlag(Identifier id, Registry<V> registry) {
		return null;
	}

	@Override
	public FlagType.Set<UUID> createUuidSetFlag(Identifier id) {
		return null;
	}

	@Override
	public <E extends Enum<E>> FlagType.Set<E> createEnumSetFlag(Identifier id, Class<E> enumClass) {
		return new SetFlagTypeImpl.EnumImpl<>(id, enumClass);
	}

	@Override
	public <V> FlagType.Set<V> createRegistryEntrySet(Identifier id, Class<V> entryClass, Registry<V> registry) {
		return new SetFlagTypeImpl.RegistryEntryImpl<>(id, entryClass, registry);
	}
}

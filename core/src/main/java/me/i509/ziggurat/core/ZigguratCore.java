package me.i509.ziggurat.core;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.actor.PlayerActor;
import me.i509.ziggurat.api.flag.FlagType;
import me.i509.ziggurat.core.duck.GameSessionDuck;
import me.i509.ziggurat.internal.Implementation;

public final class ZigguratCore implements Implementation {
	@Override
	public GameSession getSession(MinecraftServer server) throws IllegalStateException {
		final GameSessionImpl session = ((GameSessionDuck) server).getGameSession();
		if (session != null) return session;

		throw new IllegalStateException(); // TODO: Msg
	}

	@Override
	public FlagType.Bool createBoolFlag(Identifier id) {
		return FlagTypeImpl.createBool(id);
	}

	@Override
	public FlagType.Int getOrCreateIntFlag(Identifier id, int minimumBound, int maximumBound) {
		return FlagTypeImpl.createInt(id, minimumBound, maximumBound);
	}

	@Override
	public FlagType.Double getOrCreateDoubleFlag(Identifier id, double minimumBound, double maximumBound) {
		return FlagTypeImpl.createDouble(id, minimumBound, maximumBound);
	}

	@Override
	public FlagType.Uuid getOrCreateUuidFlag(Identifier id) {
		return FlagTypeImpl.createUuid(id);
	}

	@Override
	public <E extends Enum<E>> FlagType.Enum<E> getOrCreateEnumFlag(Identifier id, Class<E> enumClass) {
		return FlagTypeImpl.createEnum(id, enumClass);
	}

	@Override
	public <V> FlagType.RegistryEntry<V> getOrCreateRegistryEntryFlag(Identifier id, Registry<V> registry) {
		return FlagTypeImpl.createRegistryEntry(id, registry);
	}

	@Override
	public FlagType.Set<UUID> getOrCreateUuidSetFlag(Identifier id) {
		Objects.requireNonNull(id);

		return SetFlagTypeImpl.createUuidSet(id);
	}

	@Override
	public <E extends Enum<E>> FlagType.Set<E> getOrCreateEnumSetFlag(Identifier id, Class<E> enumClass) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(enumClass);

		if (!enumClass.isEnum()) {
			throw new IllegalArgumentException("Class must be an enum!");
		}

		return SetFlagTypeImpl.createEnumSet(id, enumClass);
	}

	@Override
	public <V> FlagType.Set<V> getOrCreateRegistryEntrySetFlag(Identifier id, Class<V> entryClass, Registry<V> registry) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(entryClass);
		Objects.requireNonNull(registry);

		return SetFlagTypeImpl.createRegistryEntrySet(id, entryClass, registry);
	}

	@Override
	public PlayerActor getPlayerActor(ServerPlayerEntity player) {
		return null;
	}
}

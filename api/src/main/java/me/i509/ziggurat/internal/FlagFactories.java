package me.i509.ziggurat.internal;

import java.util.UUID;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.api.flag.FlagType;

public interface FlagFactories {
	FlagType.Bool getOrCreateBoolFlag(Identifier id);

	FlagType.Int getOrCreateIntFlag(Identifier id, int minimumBound, int maximumBound);

	FlagType.Double getOrCreateDoubleFlag(Identifier id, double minimumBound, double maximumBound);

	FlagType.Uuid getOrCreateUuidFlag(Identifier id);

	FlagType.Str getOrCreateStringFlag(Identifier id);

	<E extends Enum<E>> FlagType.Enum<E> getOrCreateEnumFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.RegistryEntry<V> getOrCreateRegistryEntryFlag(Identifier id, Registry<V> registry);

	FlagType.Set<UUID> getOrCreateUuidSetFlag(Identifier id);

	<E extends Enum<E>> FlagType.Set<E> getOrCreateEnumSetFlag(Identifier id, Class<E> enumClass);

	<V> FlagType.Set<V> getOrCreateRegistryEntrySetFlag(Identifier id, Class<V> entryClass, Registry<V> registry);
}

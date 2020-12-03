package me.i509.ziggurat.api.flag;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

/**
 * Represents a type of flag.
 * A flag is simply a keyed object which may be used to obtain a value.
 */
public interface FlagType {
	Identifier getId();

	interface Bool extends FlagType {
	}

	interface Int extends FlagType {
		int getMinimumBound();

		int getMaximumBound();
	}

	interface Double extends FlagType {
		double getMinimumBound();

		double getMaximumBound();
	}

	interface Uuid extends FlagType {
	}

	interface Str extends FlagType {
	}

	interface Enum<E extends java.lang.Enum<E>> extends FlagType {
		Class<E> getEnumClass();
	}

	interface RegistryEntry<V> extends FlagType {
		RegistryKey<Registry<V>> getRegistryKey();
	}

	interface Set<V> extends FlagType {
		SetValueType getValueType();

		Class<? extends V> getValueClass();
	}

	enum SetValueType {
		ENUM,
		REGISTRY_ENTRY,
		UUID
	}
}

package me.i509.ziggurat.core;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.UUID;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.api.flag.FlagType;

abstract class SetFlagTypeImpl<V> extends FlagTypeImpl implements FlagType.Set<V> {
	static FlagType.Set<UUID> createUuidSet(Identifier id) {
		return new UuidImpl(id);
	}

	static <E extends java.lang.Enum<E>> FlagType.Set<E> createEnumSet(Identifier id, Class<E> enumClass) {
		return new EnumImpl<>(id, enumClass);
	}

	static <V> FlagType.Set<V> createRegistryEntrySet(Identifier id, Class<V> valueClass, Registry<V> registry) {
		return new RegistryEntryImpl<>(id, valueClass, registry);
	}

	private final Class<V> valueClass;

	SetFlagTypeImpl(Identifier id, Class<V> valueClass) {
		super(id);
		this.valueClass = valueClass;
	}

	@Override
	public Class<? extends V> getValueClass() {
		return this.valueClass;
	}

	abstract java.util.Set<V> createSet();

	private static final class UuidImpl extends SetFlagTypeImpl<UUID> {
		UuidImpl(Identifier id) {
			super(id, UUID.class);
		}

		@Override
		public SetValueType getValueType() {
			return SetValueType.UUID;
		}

		@Override
		java.util.Set<UUID> createSet() {
			return new HashSet<>();
		}
	}

	private static final class EnumImpl<E extends java.lang.Enum<E>> extends SetFlagTypeImpl<E> {
		EnumImpl(Identifier id, Class<E> enumClass) {
			super(id, enumClass);
		}

		@Override
		public SetValueType getValueType() {
			return SetValueType.ENUM;
		}

		@Override
		java.util.Set<E> createSet() {
			//noinspection unchecked
			return EnumSet.noneOf((Class<E>) this.getValueClass());
		}
	}

	/**
	 * A set flag implementation which compares keys by identity which is suitable for registry entries.
	 */
	private static final class RegistryEntryImpl<V> extends SetFlagTypeImpl<V> {
		private final Registry<V> registry;

		RegistryEntryImpl(Identifier id, Class<V> valueClass, Registry<V> registry) {
			super(id, valueClass);
			this.registry = registry;
		}

		@Override
		java.util.Set<V> createSet() {
			return Collections.newSetFromMap(new IdentityHashMap<>());
		}

		@Override
		public SetValueType getValueType() {
			return SetValueType.REGISTRY_ENTRY;
		}

		Registry<V> getRegistry() {
			return this.registry;
		}
	}
}

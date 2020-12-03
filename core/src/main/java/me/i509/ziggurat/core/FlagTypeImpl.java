package me.i509.ziggurat.core;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import me.i509.ziggurat.api.flag.FlagType;

abstract class FlagTypeImpl implements FlagType {
	static FlagType.Bool createBool(Identifier id) {
		return new BoolImpl(id);
	}

	static FlagType.Int createInt(Identifier id, int minBound, int maxBound) {
		return new IntImpl(id, minBound, maxBound);
	}

	static FlagType.Double createDouble(Identifier id, double minBound, double maxBound) {
		return new DoubleImpl(id, minBound, maxBound);
	}

	static FlagType.Uuid createUuid(Identifier id) {
		return new UuidImpl(id);
	}

	static FlagType.Str createString(Identifier id) {
		return new StrImpl(id);
	}

	static <E extends java.lang.Enum<E>> FlagType.Enum<E> createEnum(Identifier id, Class<E> enumClass) {
		return new EnumImpl<>(id, enumClass);
	}

	static <V> FlagType.RegistryEntry<V> createRegistryEntry(Identifier id, Registry<V> registry) {
		return new RegistryEntryImpl<>(id, registry);
	}

	private final Identifier id;

	FlagTypeImpl(Identifier id) {
		this.id = id;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}

	private static final class BoolImpl extends FlagTypeImpl implements FlagType.Bool {
		BoolImpl(Identifier id) {
			super(id);
		}
	}

	private static final class IntImpl extends FlagTypeImpl implements FlagType.Int {
		private final int minBound;
		private final int maxBound;

		private IntImpl(Identifier id, int minBound, int maxBound) {
			super(id);
			this.minBound = minBound;
			this.maxBound = maxBound;
		}

		@Override
		public int getMinimumBound() {
			return this.minBound;
		}

		@Override
		public int getMaximumBound() {
			return this.maxBound;
		}
	}

	private static final class DoubleImpl extends FlagTypeImpl implements FlagType.Double {
		private final double minBound;
		private final double maxBound;

		DoubleImpl(Identifier id, double minBound, double maxBound) {
			super(id);
			this.minBound = minBound;
			this.maxBound = maxBound;
		}

		@Override
		public double getMinimumBound() {
			return this.minBound;
		}

		@Override
		public double getMaximumBound() {
			return this.maxBound;
		}
	}

	private static final class UuidImpl extends FlagTypeImpl implements FlagType.Uuid {
		UuidImpl(Identifier id) {
			super(id);
		}
	}

	private static final class StrImpl extends FlagTypeImpl implements FlagType.Str {
		StrImpl(Identifier id) {
			super(id);
		}
	}

	private static final class EnumImpl<E extends java.lang.Enum<E>> extends FlagTypeImpl implements FlagType.Enum<E> {
		private Class<E> enumClass;

		EnumImpl(Identifier id, Class<E> enumClass) {
			super(id);
			this.enumClass = enumClass;
		}

		@Override
		public Class<E> getEnumClass() {
			return this.enumClass;
		}
	}

	private static final class RegistryEntryImpl<V> extends FlagTypeImpl implements FlagType.RegistryEntry<V> {
		private final RegistryKey<Registry<V>> key;

		RegistryEntryImpl(Identifier id, Registry<V> registry) {
			super(id);
			//noinspection unchecked
			this.key = (RegistryKey<Registry<V>>) registry.getKey();
		}

		@Override
		public RegistryKey<Registry<V>> getRegistryKey() {
			return this.key;
		}
	}
}

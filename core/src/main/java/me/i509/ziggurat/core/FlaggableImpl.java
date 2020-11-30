package me.i509.ziggurat.core;

import java.util.Collections;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import it.unimi.dsi.fastutil.objects.Reference2BooleanMap;
import it.unimi.dsi.fastutil.objects.Reference2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2DoubleMap;
import it.unimi.dsi.fastutil.objects.Reference2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;

import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import me.i509.ziggurat.api.flag.FlagSetResult;
import me.i509.ziggurat.api.flag.FlagType;
import me.i509.ziggurat.api.flag.FlagVisitor;
import me.i509.ziggurat.api.flag.Flaggable;

public abstract class FlaggableImpl implements Flaggable {
	private final Set<FlagType> flags = Collections.newSetFromMap(new IdentityHashMap<>());
	private final Reference2BooleanMap<FlagType.Bool> booleanFlags = new Reference2BooleanOpenHashMap<>();
	private final Reference2IntMap<FlagType.Int> intFlags = new Reference2IntOpenHashMap<>();
	private final Reference2DoubleMap<FlagType.Double> doubleFlags = new Reference2DoubleOpenHashMap<>();
	private final Map<FlagType.Uuid, UUID> uuidFlags = new IdentityHashMap<>();
	private final Map<FlagType.Enum<?>, Enum<?>> enumFlags = new IdentityHashMap<>();
	private final Map<RegistryKey<Registry<?>>, Map<FlagType.RegistryEntry<?>, ?>> registryEntries = new IdentityHashMap<>();
	private final Map<FlagType.SetValueType, Map<FlagType.Set<?>, Set<?>>> setFlags = Util.make(new EnumMap<>(FlagType.SetValueType.class), map -> {
		map.put(FlagType.SetValueType.UUID, new IdentityHashMap<>());
		map.put(FlagType.SetValueType.ENUM, new IdentityHashMap<>());
		map.put(FlagType.SetValueType.REGISTRY_ENTRY, new IdentityHashMap<>());
	});

	@Override
	public boolean getBoolean(FlagType.Bool flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			return this.booleanFlags.getBoolean(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public int getInt(FlagType.Int flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			return this.intFlags.getInt(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public double getDouble(FlagType.Double flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			return this.doubleFlags.getDouble(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public UUID getUuid(FlagType.Uuid flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			return this.uuidFlags.get(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public <V> V getRegistryEntry(FlagType.RegistryEntry<V> flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			//noinspection unchecked
			return (V) this.registryEntries.get(flag.getRegistryKey()).get(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public <E extends Enum<E>> E getEnum(FlagType.Enum<E> flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			//noinspection unchecked
			return (E) this.enumFlags.get(flag);
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public <V> Set<V> getSet(FlagType.Set<V> flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (this.isSet(flag)) {
			//noinspection unchecked
			return Collections.unmodifiableSet((Set<V>) this.setFlags.get(flag.getValueType()).get(flag));
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public FlagSetResult unset(FlagType flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		if (!this.flags.remove(flag)) {
			return FlagSetResult.SUCCESS;
		}

		return null;
	}

	@Override
	public boolean isSet(FlagType flag) {
		Objects.requireNonNull(flag, "Flag cannot be null");

		return this.flags.contains(flag);
	}

	@Override
	public void accept(FlagVisitor visitor) {
		Objects.requireNonNull(visitor, "FlagVisitor cannot be null");

		for (Reference2BooleanMap.Entry<FlagType.Bool> entry : this.booleanFlags.reference2BooleanEntrySet()) {
			if (visitor.visit(entry.getKey())) {
				visitor.visitBoolean(entry.getKey(), entry.getBooleanValue());
			}
		}

		for (Reference2IntMap.Entry<FlagType.Int> entry : this.intFlags.reference2IntEntrySet()) {
			if (visitor.visit(entry.getKey())) {
				visitor.visitInt(entry.getKey(), entry.getIntValue());
			}
		}

		for (Reference2DoubleMap.Entry<FlagType.Double> entry : this.doubleFlags.reference2DoubleEntrySet()) {
			if (visitor.visit(entry.getKey())) {
				visitor.visitDouble(entry.getKey(), entry.getDoubleValue());
			}
		}

		for (Map.Entry<FlagType.Uuid, UUID> entry : this.uuidFlags.entrySet()) {
			if (visitor.visit(entry.getKey())) {
				visitor.visitUuid(entry.getKey(), entry.getValue());
			}
		}

		for (Map<FlagType.RegistryEntry<?>, ?> value : this.registryEntries.values()) {
			for (Map.Entry<FlagType.RegistryEntry<?>, ?> entry : value.entrySet()) {
				if (visitor.visit(entry.getKey())) {
					//noinspection unchecked,rawtypes
					visitor.visitRegistryEntry((FlagType.RegistryEntry) entry.getKey(), entry.getValue());
				}
			}
		}

		for (Map.Entry<FlagType.Enum<?>, Enum<?>> entry : this.enumFlags.entrySet()) {
			if (visitor.visit(entry.getKey())) {
				//noinspection unchecked,rawtypes
				visitor.visitEnum(entry.getKey(), (Enum) entry.getValue());
			}
		}

		for (Map<FlagType.Set<?>, Set<?>> category : this.setFlags.values()) {
			for (Map.Entry<FlagType.Set<?>, Set<?>> entry : category.entrySet()) {
				if (visitor.visit(entry.getKey())) {
					if (visitor.visitSet(entry.getKey())) {
						for (Object value : entry.getValue()) {
							//noinspection unchecked,rawtypes
							visitor.visitSetValue((FlagType.Set) entry.getKey(), value);
						}
					}
				}
			}
		}
	}
}

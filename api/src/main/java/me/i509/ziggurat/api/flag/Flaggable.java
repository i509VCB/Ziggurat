package me.i509.ziggurat.api.flag;

import java.util.Set;
import java.util.UUID;

/**
 * An object which may contain {@link FlagType flags}.
 */
public interface Flaggable {
	boolean getBoolean(FlagType.Bool flag);

	int getInt(FlagType.Int flag);

	double getDouble(FlagType.Double flag);

	UUID getUuid(FlagType.Uuid flag);

	String getString(FlagType.Str flag);

	<V> V getRegistryEntry(FlagType.RegistryEntry<V> flag);

	<E extends Enum<E>> E getEnum(FlagType.Enum<E> flag);

	<V> Set<V> getSet(FlagType.Set<V> flag);

	boolean isSet(FlagType flag);

	FlagSetResult unset(FlagType flag);

	void accept(FlagVisitor visitor);
}

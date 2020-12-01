package me.i509.ziggurat.api.flag;

import java.util.UUID;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

public final class FlagFactory {
	static {
		Flags.init();
	}

	public static FlagType.Bool ofBool(Identifier id) {
		return ZigguratImplementation.requireImpl().createBoolFlag(id);
	}

	public static FlagType.Int ofInt(Identifier id) {
		return ofInt(id, Integer.MIN_VALUE);
	}

	public static FlagType.Int ofInt(Identifier id, int minimumBound) {
		return ofInt(id, minimumBound, Integer.MAX_VALUE);
	}

	public static FlagType.Int ofInt(Identifier id, int minimumBound, int maximumBound) {
		return ZigguratImplementation.requireImpl().createIntFlag(id, minimumBound, maximumBound);
	}

	public static FlagType.Double ofDouble(Identifier id) {
		return ofDouble(id, Double.MIN_VALUE);
	}

	public static FlagType.Double ofDouble(Identifier id, double minimumBound) {
		return ofDouble(id, minimumBound, Double.MAX_VALUE);
	}

	public static FlagType.Double ofDouble(Identifier id, double minimumBound, double maximumBound) {
		return ZigguratImplementation.requireImpl().createDoubleFlag(id, minimumBound, maximumBound);
	}

	public static FlagType.Uuid ofUuid(Identifier id) {
		return ZigguratImplementation.requireImpl().createUuidFlag(id);
	}

	public static <E extends Enum<E>> FlagType.Enum<E> ofEnum(Identifier id, Class<E> enumClass) {
		return ZigguratImplementation.requireImpl().createEnumFlag(id, enumClass);
	}

	public static <V> FlagType.RegistryEntry<V> ofRegistryEntry(Identifier id, Registry<V> registry) {
		return ZigguratImplementation.requireImpl().createRegistryEntryFlag(id, registry);
	}

	public static FlagType.Set<UUID> ofUuidSet(Identifier id) {
		return ZigguratImplementation.requireImpl().createUuidSetFlag(id);
	}

	public static <E extends Enum<E>> FlagType.Set<E> ofEnumSet(Identifier id, Class<E> enumClass) {
		return ZigguratImplementation.requireImpl().createEnumSetFlag(id, enumClass);
	}

	public static <V> FlagType.Set<V> ofRegistryEntrySet(Identifier id, Class<V> entryClass, Registry<V> registry) {
		return ZigguratImplementation.requireImpl().createRegistryEntrySet(id, entryClass, registry);
	}

	private FlagFactory() {
		Uninstiantable.whyDoIHearBossMusic(FlagFactory.class);
	}
}

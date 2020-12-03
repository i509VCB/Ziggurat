package me.i509.ziggurat.api.flag;

import java.util.UUID;

import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

import me.i509.ziggurat.api.region.GlobalRegion;
import me.i509.ziggurat.api.region.WorldRegion;
import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

/**
 * An enumeration of all builtin flags.
 * @see FlagFactory
 */
public final class Flags {
	// SORTFIELDS:ON

	/**
	 * A flag which represents a boolean specifying whether fire can spread within a region.
	 */
	public static final FlagType.Bool CAN_FIRE_SPREAD = FlagFactory.ofBool(ZigguratImplementation.id("can_fire_spread"));

	/**
	 * A flag which represents a boolean specifying whether a nether portal can generate within a region.
	 */
	public static final FlagType.Bool CAN_GENERATE_NETHER_PORTAL = FlagFactory.ofBool(ZigguratImplementation.id("can_generate_nether_portal"));

	/**
	 * A flag which represents a set of all {@linkplain EntityType types of entities} that may spawn within a region.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"}) // Thank you generics
	public static final FlagType.Set<EntityType<?>> CAN_SPAWN_ENTITY = FlagFactory.ofRegistryEntrySet(ZigguratImplementation.id("can_spawn"), (Class<EntityType<?>>) (Class) EntityType.class, Registry.ENTITY_TYPE);

	/**
	 * A flag type which represents the {@linkplain UUID uuid} of the player which owns a region.
	 * This flag is not applicable in any {@linkplain WorldRegion world} or {@linkplain GlobalRegion global} regions.
	 */
	public static final FlagType.Uuid OWNER = FlagFactory.ofUuid(ZigguratImplementation.id("owner"));

	// SORTFIELDS:OFF

	static void init() {
	}

	private Flags() {
		Uninstiantable.whyDoIHearBossMusic(Flags.class);
	}
}

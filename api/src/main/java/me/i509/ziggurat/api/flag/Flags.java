package me.i509.ziggurat.api.flag;

import static me.i509.ziggurat.internal.ZigguratImplementation.id;

import java.util.UUID;

import me.i509.ziggurat.api.region.GlobalRegion;
import me.i509.ziggurat.api.region.WorldRegion;
import me.i509.ziggurat.internal.Uninstiantable;

import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.PortalForcer;

/**
 * An enumeration of all builtin flags.
 * @see FlagFactory
 */
public final class Flags {
	// TODO: Flag ideas
	//  Block breaking
	//  Block placing
	//  Entity damaged? (May be hard)
	//  Entity death? (May be hard)
	//  Open container
	//  Taking item out of item frame (Entity interaction?)
	//  Rotating item frame (Entity interaction?)
	//  Block interaction
	//  Block attack
	//  Use pressure plate
	//  Decreasing hunger
	//  Fall damage
	//  Shoot projectile (Using item or entity for filtering?)
	//  Set respawn point (Needs a think)
	//  Entity interaction
	//  Use item

	// SORTFIELDS:ON

	/**
	 * A flag which represents a boolean specifying whether explosions within a region can break blocks.
	 */
	public static final FlagType.Bool CAN_EXPLOSION_BREAK_BLOCKS = FlagFactory.ofBool(id("can_explosion_break_blocks"));

	/**
	 * A flag which represents a boolean specifying whether explosions within a region can damage entities.
	 */
	public static final FlagType.Bool CAN_EXPLOSION_DAMAGE_ENTITIES = FlagFactory.ofBool(id("can_explosion_damage_entities"));

	/**
	 * A flag which represents a boolean specifying whether fire can spread within a region.
	 */
	public static final FlagType.Bool CAN_FIRE_SPREAD = FlagFactory.ofBool(id("can_fire_spread"));

	/**
	 * A flag which represents a boolean specifying whether a nether portal can generate within a region.
	 * If the flag value is false, nether portal creation will be cancelled or the {@link PortalForcer} will find a new position to spawn the portal at.
	 */
	public static final FlagType.Bool CAN_GENERATE_NETHER_PORTAL = FlagFactory.ofBool(id("can_generate_nether_portal"));

	/**
	 * A flag which represents a set of all {@linkplain EntityType types of entities} that may spawn within a region.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"}) // Thank you generics
	public static final FlagType.Set<EntityType<?>> CAN_SPAWN_ENTITY = FlagFactory.ofRegistryEntrySet(id("can_spawn_entity"), (Class<EntityType<?>>) (Class) EntityType.class, Registry.ENTITY_TYPE);

	/**
	 * A flag type which represents the {@linkplain UUID uuid} of the player which owns a region.
	 * This flag is not applicable in any {@linkplain WorldRegion world} or {@linkplain GlobalRegion global} regions.
	 */
	public static final FlagType.Uuid OWNER = FlagFactory.ofUuid(id("owner"));

	// SORTFIELDS:OFF

	static void init() {
	}

	private Flags() {
		Uninstiantable.whyDoIHearBossMusic(Flags.class);
	}
}

package me.i509.ziggurat.api.flag;

import java.util.UUID;

import me.i509.ziggurat.api.region.GlobalRegion;
import me.i509.ziggurat.api.region.WorldRegion;
import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

/**
 * An enumeration of all builtin flags.
 * @see FlagFactory
 */
public final class Flags {
	/**
	 * A flag type which represents the {@linkplain UUID uuid} of the player which owns a region.
	 * This flag is not applicable in any {@linkplain WorldRegion world} or {@linkplain GlobalRegion global} regions.
	 */
	public static final FlagType.Uuid OWNER = FlagFactory.ofUuid(ZigguratImplementation.id("owner"));

	static void init() {
	}

	private Flags() {
		Uninstiantable.whyDoIHearBossMusic(Flags.class);
	}
}

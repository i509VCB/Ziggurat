package me.i509.ziggurat.api.region;

/**
 * An enum which defines the type of a region.
 */
public enum RegionType {
	/**
	 * Represents a local region.
	 * A local region is inside of a world region.
	 */
	LOCAL,
	/**
	 * Represents a {@link WorldRegion}.
	 */
	WORLD,
	/**
	 * Represents a {@link GlobalRegion}.
	 */
	GLOBAL
}

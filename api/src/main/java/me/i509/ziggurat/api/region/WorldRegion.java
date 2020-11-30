package me.i509.ziggurat.api.region;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import me.i509.ziggurat.api.flag.Flaggable;

/**
 * A region which represents the entirety of a world.
 */
public interface WorldRegion extends Region {
	Identifier getWorldId();

	RegistryKey<World> getWorldKey();

	void accept(int x, int y, int z, Flaggable.Visitor visitor);

	void accept(Box box, Flaggable.Visitor visitor);
}

package me.i509.ziggurat.api;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import me.i509.ziggurat.api.region.GlobalRegion;
import me.i509.ziggurat.api.region.WorldRegion;

public interface GameSession {
	GlobalRegion getGlobalRegion();

	WorldRegion getWorldRegion(Identifier id);

	WorldRegion getWorldRegion(RegistryKey<World> key);

	WorldRegion getWorldRegion(ServerWorld world);
}

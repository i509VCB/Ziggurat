package me.i509.ziggurat.core;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.region.GlobalRegion;
import me.i509.ziggurat.api.region.WorldRegion;

public final class GameSessionImpl implements GameSession {
	@Override
	public GlobalRegion getGlobalRegion() {
		return null;
	}

	@Override
	public WorldRegion getWorldRegion(Identifier id) {
		return null;
	}

	@Override
	public WorldRegion getWorldRegion(RegistryKey<World> key) {
		return null;
	}

	@Override
	public WorldRegion getWorldRegion(ServerWorld world) {
		return null;
	}
}

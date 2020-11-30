package me.i509.ziggurat.api;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import me.i509.ziggurat.api.region.WorldRegion;
import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

public final class Ziggurat {
	public static GameSession getSession(MinecraftServer server) throws IllegalStateException {
		return ZigguratImplementation.requireImpl().getSession(server);
	}

	public static WorldRegion getRegion(ServerWorld world) {
		return getSession(world.getServer()).getWorldRegion(world);
	}

	private Ziggurat() {
		Uninstiantable.whyDoIHearBossMusic(Ziggurat.class);
	}
}

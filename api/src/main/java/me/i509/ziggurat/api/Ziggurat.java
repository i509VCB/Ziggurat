package me.i509.ziggurat.api;

import java.util.Objects;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import me.i509.ziggurat.api.region.WorldRegion;
import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

/**
 * The main API class for Ziggurat.
 * This may be used to get access to the game session.
 *
 * <p>This is provided for convince, but you are encouraged to register an {@linkplain ZigguratIntegration integration} to be notified when the game session is available.
 * @see ZigguratIntegration
 */
public final class Ziggurat {
	/**
	 * Gets the current game session.
	 *
	 * @param server the server
	 * @return the current game session
	 * @throws IllegalStateException if the game session is not available
	 */
	public static GameSession getSession(MinecraftServer server) throws IllegalStateException {
		Objects.requireNonNull(server, "Minecraft server cannot be null");

		return ZigguratImplementation.requireImpl().getSession(server);
	}

	/**
	 * A convince method to get the world region from a server world.
	 *
	 * @param world the server world
	 * @return
	 */
	public static WorldRegion getRegion(ServerWorld world) {
		Objects.requireNonNull(world, "ServerWorld cannot be null");

		return getSession(world.getServer()).getWorldRegion(world);
	}

	private Ziggurat() {
		Uninstiantable.whyDoIHearBossMusic(Ziggurat.class);
	}
}

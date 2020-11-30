package me.i509.ziggurat.api;

import net.minecraft.server.MinecraftServer;

/**
 * The entrypoint for an integration with ziggurat,
 * Mods may use this to be notified when a ziggurat implementation is present.
 * This interface is an entrypoint and is keyed as {@code ziggurat:integration}.
 *
 * <p>Below is an example of how an integration is specified in a {@code fabric.mod.json}.
 * <pre>{@code
 * "entrypoints": {
 * 	"ziggurat:integration": [
 * 		"mymod.integration.MyModZigguratIntegration"
 * 	]
 * 	...
 * }
 * }</pre>
 *
 * @see net.fabricmc.loader.api.FabricLoader#getEntrypointContainers(String, Class).
 */
public interface ZigguratIntegration {
	/**
	 * Gets the name of this integration for identifying each individual integration for a mod.
	 * This should only contain the name of the integration and does not need to contain the mod name, since Fabric Loader will let Ziggurat know which mod has provided the integration instance.
	 *
	 * @return the integration name
	 */
	String getIntegrationName();

	/**
	 * Called when Ziggurat is ready to initialize integrations.
	 * If this method is called, Ziggurat is guaranteed to be loaded and other mods may initialize their own integration logic.
	 *
	 * @return whether the mod wishes to enable this integration.
	 * @apiNote if {@code false} is returned by this method, this integration will receive no future calls when a {@linkplain ZigguratIntegration#initSession(GameSession) session is initialized}.
	 */
	boolean init();

	/**
	 * Called when Ziggurat has initialized the game session.
	 * An integration may use this to cache the current game session, or use this as notification for when {@link me.i509.ziggurat.api.Ziggurat#getSession(MinecraftServer)} is available.
	 *
	 * @param session the session that has been initialized
	 */
	void initSession(GameSession session);

	/**
	 * Called when Ziggurat has stopped the game session.
	 * An integration may use this to clean up references the current game session.
	 * {@link me.i509.ziggurat.api.Ziggurat#getSession(MinecraftServer)} will throw an exception if called at this point.
	 *
	 * @param session the session that has ended
	 */
	void endSession(GameSession session);
}

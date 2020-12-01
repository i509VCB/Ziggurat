package me.i509.ziggurat.api;

import net.minecraft.server.MinecraftServer;

import net.fabricmc.loader.api.FabricLoader;

/**
 * The entrypoint for an integration with ziggurat,
 * This interface is an {@linkplain FabricLoader#getEntrypointContainers(String, Class) entrypoint} and the key is {@code ziggurat:integration}.
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
 * <p>An integration only has to implement two methods, being {@linkplain #getIntegrationName() the integration name} and {@linkplain #init() initialization}.
 * Other methods in the integration class may be useful, such as getting notified when a game session is {@linkplain #initSession(GameSession) initialized} or {@linkplain #endSession(GameSession) ended}.
 *
 * @see FabricLoader#getEntrypointContainers(String, Class)
 */
public interface ZigguratIntegration {
	/**
	 * Gets the name of this integration for identifying each individual integration for a mod.
	 *
	 * <p>This should only contain the name of the integration and does not need to contain the mod name.
	 * Fabric Loader will let Ziggurat know which mod has provided the integration instance, so including the mod name is redundant.
	 *
	 * @return the integration name
	 */
	String getIntegrationName();

	/**
	 * Called when Ziggurat is ready to initialize integrations.
	 * If this method is called, Ziggurat is guaranteed to be loaded and other mods may initialize their own integration logic.
	 *
	 * @return whether the mod wishes to enable this integration.
	 * @apiNote if {@code false} is returned by this method, this integration will receive no further notifications.
	 */
	boolean init();

	/**
	 * Called when an integration should attach it's flags to the type of region desired.
	 *
	 * @param registrar the flag attachment registrar
	 */
	default void registerFlagAttachments(FlagAttachmentRegistrar registrar) {
	}

	/**
	 * Called when Ziggurat has initialized the game session.
	 * An integration may use this to cache the current game session, or use this as notification for when {@link me.i509.ziggurat.api.Ziggurat#getSession(MinecraftServer)} is available.
	 *
	 * @param session the session that has been initialized
	 */
	default void initSession(GameSession session) {
	}

	/**
	 * Called when Ziggurat has stopped the game session.
	 * An integration may use this to clean up references the current game session.
	 * {@link me.i509.ziggurat.api.Ziggurat#getSession(MinecraftServer)} will throw an exception if called at this point.
	 *
	 * @param session the session that has ended
	 */
	default void endSession(GameSession session) {
	}
}

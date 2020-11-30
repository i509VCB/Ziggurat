package me.i509.ziggurat.internal;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import me.i509.ziggurat.api.Ziggurat;

/**
 * This is an internal implementation class for ziggurat.
 * You should not be using this at all!
 */
@ApiStatus.Internal
public final class ZigguratImplementation {
	private static final Logger LOGGER = LogManager.getFormatterLogger(Ziggurat.class);
	private static Implementation implementation;

	/**
	 * Gets the implementation, throwing an exception if it was not available.
	 *
	 * @return the implementation
	 */
	public static Implementation requireImpl() {
		if (implementation != null) {
			return implementation;
		}

		throw new IllegalStateException("Ziggurat Implementation not set!");
	}

	public static void setImplementation(Implementation implementation) {
		Objects.requireNonNull(implementation, "Tried to set \"null\" as the implementation");

		if (ZigguratImplementation.implementation != null) {
			throw new UnsupportedOperationException("Cannot replace ziggurat implementation at runtime");
		}

		LOGGER.info("Set Ziggurat implementation to {}", implementation);
		ZigguratImplementation.implementation = implementation;
	}

	private ZigguratImplementation() {
		Uninstiantable.whyDoIHearBossMusic(ZigguratImplementation.class);
	}
}

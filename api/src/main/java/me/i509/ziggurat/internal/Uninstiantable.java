package me.i509.ziggurat.internal;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class Uninstiantable {
	public static void whyDoIHearBossMusic(Class<?> victim) {
		throw new AssertionError(String.format("You should not be instantiating %s", victim));
	}

	private Uninstiantable() {
		throw new AssertionError("Why are you even doing this?");
	}
}

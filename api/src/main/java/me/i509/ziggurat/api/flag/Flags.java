package me.i509.ziggurat.api.flag;

import me.i509.ziggurat.internal.Uninstiantable;

public final class Flags {
	static void init() {
	}

	private Flags() {
		Uninstiantable.whyDoIHearBossMusic(Flags.class);
	}
}

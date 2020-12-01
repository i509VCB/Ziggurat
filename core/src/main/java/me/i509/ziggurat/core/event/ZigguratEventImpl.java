package me.i509.ziggurat.core.event;

import me.i509.ziggurat.internal.Uninstiantable;
import me.i509.ziggurat.internal.ZigguratImplementation;

public final class ZigguratEventImpl {
	public static void initEvents() {
		try {
			ZigguratImplementation.requireImpl();
		} catch (Exception ignored) {
			internalInit();
			return;
		}

		throw new IllegalStateException("Tried to initialize Ziggurat's event implementation a second time");
	}

	private static void internalInit() {

	}

	private ZigguratEventImpl() {
		Uninstiantable.whyDoIHearBossMusic(ZigguratEventImpl.class);
	}
}

package me.i509.ziggurat.core.entrypoint;

import net.fabricmc.api.ModInitializer;

import me.i509.ziggurat.core.ZigguratCore;

public final class ZigguratMod implements ModInitializer {
	@Override
	public void onInitialize() {
		new ZigguratCore();
	}
}

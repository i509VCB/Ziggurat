package me.i509.ziggurat.core.event;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

import me.i509.ziggurat.core.command.Commands;
import me.i509.ziggurat.internal.Uninstiantable;

final class PlatformEvents {
	static void registerPlatformEvents() {
		CommandRegistrationCallback.EVENT.register(PlatformEvents::registerCommands);
	}

	private static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		Commands.create(dispatcher, dedicated);
	}

	private PlatformEvents() {
		Uninstiantable.whyDoIHearBossMusic(PlatformEvents.class);
	}
}

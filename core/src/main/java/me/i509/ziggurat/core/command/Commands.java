package me.i509.ziggurat.core.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

import me.i509.ziggurat.internal.Uninstiantable;

public final class Commands {
	public static void create(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {

	}

	private Commands() {
		Uninstiantable.whyDoIHearBossMusic(Commands.class);
	}
}

package me.i509.ziggurat.api.actor;

import net.minecraft.server.network.ServerPlayerEntity;

import me.i509.ziggurat.api.Ziggurat;
import me.i509.ziggurat.internal.ZigguratImplementation;

public interface PlayerActor extends EntityActor {
	static PlayerActor of(ServerPlayerEntity player) {
		return ZigguratImplementation.requireImpl().getPlayerActor(player);
	}

	String getUsername();
}

package me.i509.ziggurat.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.server.MinecraftServer;

import me.i509.ziggurat.core.GameSessionImpl;
import me.i509.ziggurat.core.duck.GameSessionDuck;

@Mixin(MinecraftServer.class)
abstract class MinecraftServerMixin implements GameSessionDuck {
	@Unique
	private GameSessionImpl session;

	@Override
	public GameSessionImpl getGameSession() {
		return this.session;
	}

	@Override
	public void setGameSession(GameSessionImpl session) {
		this.session = session;
	}
}

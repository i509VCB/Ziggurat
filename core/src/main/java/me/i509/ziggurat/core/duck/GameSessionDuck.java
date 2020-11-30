package me.i509.ziggurat.core.duck;

import org.jetbrains.annotations.Nullable;

import me.i509.ziggurat.core.GameSessionImpl;

public interface GameSessionDuck {
	@Nullable
	GameSessionImpl getGameSession();

	void setGameSession(GameSessionImpl session);
}

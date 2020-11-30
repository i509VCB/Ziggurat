package me.i509.ziggurat.api.actor;

public interface PlayerActor extends EntityActor {
	static PlayerActor of(ServerPlayerEn) {

	}

	String getUsername();
}

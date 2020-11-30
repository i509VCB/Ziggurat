package me.i509.ziggurat.api.actor;

import net.minecraft.entity.EntityType;

public interface EntityActor extends Actor {
	EntityType<?> getType();
}

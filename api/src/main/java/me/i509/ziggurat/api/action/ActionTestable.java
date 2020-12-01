package me.i509.ziggurat.api.action;

import me.i509.ziggurat.api.actor.Actor;

/**
 * Represents an object where an action can be tested with an actor.
 */
public interface ActionTestable {
	boolean canAct(Actor actor /*TODO: Action*/);
}

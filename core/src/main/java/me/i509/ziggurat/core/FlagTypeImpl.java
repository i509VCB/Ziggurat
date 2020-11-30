package me.i509.ziggurat.core;

import net.minecraft.util.Identifier;

import me.i509.ziggurat.api.flag.FlagType;

abstract class FlagTypeImpl implements FlagType {
	static void assertFlagSealed(FlagType flag) {
		if (flag instanceof FlagTypeImpl) {
			return;
		}

		throw new UnsupportedOperationException(); // TODO: Msg
	}

	private final Identifier id;

	FlagTypeImpl(Identifier id) {
		this.id = id;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}
}

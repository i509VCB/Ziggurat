package me.i509.ziggurat.api.region;

import me.i509.ziggurat.api.action.ActionTestable;
import me.i509.ziggurat.api.flag.Flaggable;

public interface Region extends ActionTestable, Flaggable {
	RegionType getType();
}

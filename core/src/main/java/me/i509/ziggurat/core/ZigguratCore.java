package me.i509.ziggurat.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

import me.i509.ziggurat.api.GameSession;
import me.i509.ziggurat.api.Ziggurat;
import me.i509.ziggurat.api.ZigguratIntegration;
import me.i509.ziggurat.api.actor.PlayerActor;
import me.i509.ziggurat.api.flag.FlagType;
import me.i509.ziggurat.core.duck.GameSessionDuck;
import me.i509.ziggurat.core.event.ZigguratEventImpl;
import me.i509.ziggurat.internal.Implementation;
import me.i509.ziggurat.internal.ZigguratImplementation;

public final class ZigguratCore implements Implementation {
	private static final Logger LOGGER = LogManager.getLogger(Ziggurat.class);
	private final List<EntrypointContainer<ZigguratIntegration>> enabledIntegrations = new ArrayList<>();
	private final FlagFactories flagFactories = new FlagFactories();

	public ZigguratCore() {
		// Do not set the implementation yet, as events are locked behind the impl being loaded to prevent double reg
		ZigguratEventImpl.initEvents();
		ZigguratImplementation.setImplementation(this);

		this.completeSetup();
	}

	private void completeSetup() {
		LOGGER.info("Loading integrations");
		final List<EntrypointContainer<ZigguratIntegration>> integrations = FabricLoader.getInstance().getEntrypointContainers("ziggurat:integration", ZigguratIntegration.class);

		for (EntrypointContainer<ZigguratIntegration> integration : integrations) {
			if (integration.getEntrypoint().init()) {
				this.enabledIntegrations.add(integration);
				LOGGER.info("Loaded integration of name \"{}\" from mod {}", integration.getEntrypoint().getIntegrationName(), integration.getProvider().getMetadata().getId());
			} else {
				LOGGER.info("Not loading integration of name \"{}\" from mod {}", integration.getEntrypoint().getIntegrationName(), integration.getProvider().getMetadata().getId());
			}
		}
	}

	@Override
	public GameSession getSession(MinecraftServer server) throws IllegalStateException {
		final GameSessionImpl session = ((GameSessionDuck) server).getGameSession();
		if (session != null) return session;

		throw new IllegalStateException(); // TODO: Msg
	}

	@Override
	public me.i509.ziggurat.internal.FlagFactories getFlagFactories() {
		return this.flagFactories;
	}

	@Override
	public PlayerActor getPlayerActor(ServerPlayerEntity player) {
		return null;
	}

	static class FlagFactories implements me.i509.ziggurat.internal.FlagFactories {
		@Override
		public FlagType.Bool getOrCreateBoolFlag(Identifier id) {
			return FlagTypeImpl.createBool(id);
		}

		@Override
		public FlagType.Int getOrCreateIntFlag(Identifier id, int minimumBound, int maximumBound) {
			return FlagTypeImpl.createInt(id, minimumBound, maximumBound);
		}

		@Override
		public FlagType.Double getOrCreateDoubleFlag(Identifier id, double minimumBound, double maximumBound) {
			return FlagTypeImpl.createDouble(id, minimumBound, maximumBound);
		}

		@Override
		public FlagType.Uuid getOrCreateUuidFlag(Identifier id) {
			return FlagTypeImpl.createUuid(id);
		}

		@Override
		public FlagType.Str getOrCreateStringFlag(Identifier id) {
			return FlagTypeImpl.createString(id);
		}

		@Override
		public <E extends Enum<E>> FlagType.Enum<E> getOrCreateEnumFlag(Identifier id, Class<E> enumClass) {
			return FlagTypeImpl.createEnum(id, enumClass);
		}

		@Override
		public <V> FlagType.RegistryEntry<V> getOrCreateRegistryEntryFlag(Identifier id, Registry<V> registry) {
			return FlagTypeImpl.createRegistryEntry(id, registry);
		}

		@Override
		public FlagType.Set<UUID> getOrCreateUuidSetFlag(Identifier id) {
			Objects.requireNonNull(id);

			return SetFlagTypeImpl.createUuidSet(id);
		}

		@Override
		public <E extends Enum<E>> FlagType.Set<E> getOrCreateEnumSetFlag(Identifier id, Class<E> enumClass) {
			Objects.requireNonNull(id);
			Objects.requireNonNull(enumClass);

			if (!enumClass.isEnum()) {
				throw new IllegalArgumentException("Class must be an enum!");
			}

			return SetFlagTypeImpl.createEnumSet(id, enumClass);
		}

		@Override
		public <V> FlagType.Set<V> getOrCreateRegistryEntrySetFlag(Identifier id, Class<V> entryClass, Registry<V> registry) {
			Objects.requireNonNull(id);
			Objects.requireNonNull(entryClass);
			Objects.requireNonNull(registry);

			return SetFlagTypeImpl.createRegistryEntrySet(id, entryClass, registry);
		}
	}
}

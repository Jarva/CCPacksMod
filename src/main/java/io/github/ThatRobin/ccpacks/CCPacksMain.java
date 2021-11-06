package io.github.ThatRobin.ccpacks;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.ThatRobin.ccpacks.choice.Choice;
import io.github.ThatRobin.ccpacks.choice.ChoiceLayers;
import io.github.ThatRobin.ccpacks.choice.ChoiceManager;
import io.github.ThatRobin.ccpacks.commands.ChoiceCommand;
import io.github.ThatRobin.ccpacks.commands.ItemActionCommand;
import io.github.ThatRobin.ccpacks.commands.SetCommand;
import io.github.ThatRobin.ccpacks.component.ItemHolderComponent;
import io.github.ThatRobin.ccpacks.component.ItemHolderComponentImpl;
import io.github.ThatRobin.ccpacks.factories.ContentFactories.*;
import io.github.ThatRobin.ccpacks.factories.*;
import io.github.ThatRobin.ccpacks.networking.CCPacksModPacketC2S;
import io.github.ThatRobin.ccpacks.power.PowerIconManager;
import io.github.ThatRobin.ccpacks.registries.CCPacksRegistry;
import io.github.ThatRobin.ccpacks.registries.ContentManager;
import io.github.ThatRobin.ccpacks.registries.TaskManager;
import io.github.ThatRobin.ccpacks.util.OnLoadResourceManager;
import io.github.ThatRobin.ccpacks.util.UniversalPowerManager;
import io.github.apace100.apoli.util.NamespaceAlias;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class CCPacksMain implements ModInitializer, EntityComponentInitializer {

	public static final Logger LOGGER = LogManager.getLogger(CCPacksMain.class);
	public static final String MODID = "ccpacks";

	public static CCPacksRegistry ccPacksRegistry = new CCPacksRegistry();

	public static PowerIconManager powerIconManager = new PowerIconManager();;

	@Override
	public void onInitialize() {
		GeckoLib.initialize();
		Choice.init();

		NamespaceAlias.addAlias(MODID, "apoli");
		NamespaceAlias.addAlias("origins", "apoli");

		EntityActions.register();
		EntityConditions.register();
		ItemActions.register();
		ItemConditions.register();
		PowerFactories.register();

		// Mob Behaviours

		//TaskFactories.register();

		// Custom Content
		BlockFactories.register();
		EnchantmentFactories.register();
		//EntityFactories.register();
		ItemFactories.register();
		KeybindFactories.register();
		ParticleFactories.register();
		PortalFactories.register();
		ProjectileFactories.register();
		SoundEventFactories.register();
		StatusEffectFactories.register();

		CCPacksModPacketC2S.register();
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			SetCommand.register(dispatcher);
			ItemActionCommand.register(dispatcher);
			ChoiceCommand.register(dispatcher);
		});

		OnLoadResourceManager.addSingleListener(new TaskManager());
		OnLoadResourceManager.addSingleListener(new ContentManager());

		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new UniversalPowerManager());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ChoiceManager());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ChoiceLayers());
	}

	public static Identifier identifier(String path) {
		return new Identifier(MODID, path);
	}

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(LivingEntity.class, ItemHolderComponent.KEY)
				.impl(ItemHolderComponentImpl.class)
				.respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY)
				.end(ItemHolderComponentImpl::new);
	}
}

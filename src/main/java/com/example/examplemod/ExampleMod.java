package com.example.examplemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.fml.ModContainer;

@Mod(ExampleMod.MODID)
public class ExampleMod {
  public static final String MODID = "examplemod";
  public static final Logger LOGGER = LogUtils.getLogger();

  public ExampleMod(IEventBus modEventBus, ModContainer modContainer) {
    LOGGER.info("Mod initializing");

    SkyEquipments.init();

    ModItems.ITEMS.register(modEventBus);
    ModItems.DATA_COMPONENTS.register(modEventBus);
    ModEntities.ENTITIES.register(modEventBus);

    modEventBus.addListener(ExampleMod::modifyComponents);
    modEventBus.addListener(ModItems::addCreativeTab);
    modEventBus.addListener(ExampleMod::registerEntityRenderers);
  }

  @SubscribeEvent
  public static void modifyComponents(ModifyDefaultComponentsEvent event) {
    event.modify(Items.TNT, (components, context, item) -> {
      components.set(DataComponents.MAX_STACK_SIZE, 8);
      components.set(DataComponents.COOKING_FUEL,
          new CookingFuel(new ResolvableInt.Constant(20 * 5), new ResolvableFloat.Constant(1F)));
    });
  }

  @SubscribeEvent
  public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(ModEntities.WANDERER.get(), NoopRenderer::new);
  }
}

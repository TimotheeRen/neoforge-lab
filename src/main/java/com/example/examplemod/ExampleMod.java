package com.example.examplemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ExampleMod.MODID)
public class ExampleMod {
  public static final String MODID = "examplemod";
  public static final Logger LOGGER = LogUtils.getLogger();

  public ExampleMod(IEventBus modEventBus, ModContainer modContainer) {
    LOGGER.info("Mod initializing");
    ModItems.ITEMS.register(modEventBus);
    ModItems.DATA_COMPONENTS.register(modEventBus);
  }
}

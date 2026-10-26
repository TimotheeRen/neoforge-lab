package com.example.examplemod;

import org.slf4j.Logger;

import com.example.examplemod.blocks.ModBlocks;
import com.example.examplemod.blocks.ModBlocksEntities;
import com.example.examplemod.entities.ModEntities;
import com.example.examplemod.items.ModItems;
import com.example.examplemod.items.SkyEquipments;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ExampleMod.MODID)
public class ExampleMod {
  public static final String MODID = "examplemod";
  public static final Logger LOGGER = LogUtils.getLogger();

  public ExampleMod(IEventBus modEventBus, ModContainer modContainer) {
    LOGGER.info("Mod initializing");

    SkyEquipments.init();

    ModItems.ITEMS.register(modEventBus);
    ModBlocks.BLOCKS.register(modEventBus);
    ModBlocksEntities.BLOCK_ENTITIES_TYPES.register(modEventBus);
    ModItems.DATA_COMPONENTS.register(modEventBus);
    ModEntities.ENTITIES.register(modEventBus);
  }
}

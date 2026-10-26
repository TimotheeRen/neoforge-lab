package com.example.examplemod.blocks;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocksEntities {
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES_TYPES = DeferredRegister.create(
      Registries.BLOCK_ENTITY_TYPE,
      ExampleMod.MODID);

  public static final Supplier<BlockEntityType<IronChestBlockEntity>> IRON_CHEST_TYPE = BLOCK_ENTITIES_TYPES.register(
      "iron_chest_type",
      () -> new BlockEntityType<>(IronChestBlockEntity::new, false, ModBlocks.IRON_CHEST.get()));
}

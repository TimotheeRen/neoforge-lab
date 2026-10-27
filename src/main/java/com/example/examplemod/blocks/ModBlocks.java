package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;

public class ModBlocks {
  public static final Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MODID);

  public static final DeferredBlock<Block> MUD = BLOCKS.register("mud_block",
      registry -> new MudBlock(BlockBehaviour.Properties.of()
          .destroyTime(2.0f)
          .explosionResistance(10.0f)
          .sound(SoundType.GRAVEL)
          .lightLevel(state -> 7)
          .setId(ResourceKey.create(Registries.BLOCK, registry))));
  public static DeferredBlock<IronChestEntityBlock> IRON_CHEST = BLOCKS.register("iron_chest",
      registry -> new IronChestEntityBlock(
          BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registry))));
  public static DeferredBlock<ConverterBlock> CONVERTER = BLOCKS.register("converter",
      registry -> new ConverterBlock(
          BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registry))));
}

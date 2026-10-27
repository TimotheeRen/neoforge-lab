package com.example.examplemod.blocks;

import com.example.examplemod.menus.ConverterMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ConverterBlock extends Block {
  public ConverterBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
      BlockHitResult hit) {
    if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
      serverPlayer.openMenu(new SimpleMenuProvider(
          (containerId, playerInventory, p) -> new ConverterMenu(containerId, playerInventory),

          Component.literal("Converter")));
    }
    return InteractionResult.SUCCESS;
  }
}

package com.example.examplemod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class EmeraldItem extends Item {
  public EmeraldItem(Item.Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult use(Level level, Player player, InteractionHand hand) {
    // ExampleMod.LOGGER.info("Item clicked!");
    if (level.isClientSide()) {
      player.sendOverlayMessage(Component.literal("Item clicked !"));
    }
    return InteractionResult.SUCCESS;
  }
}

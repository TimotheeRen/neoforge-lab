package com.example.examplemod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class IronChestBlockEntity extends BaseContainerBlockEntity {
  private static final int SIZE = 27;

  private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

  public IronChestBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlocksEntities.IRON_CHEST_TYPE.get(), pos, state);
  }

  public NonNullList<ItemStack> getItems() {
    return items;
  }

  public Component getDefaultName() {
    return Component.literal("Iron chest");
  }

  public int getContainerSize() {
    return SIZE;
  }

  public void setItems(NonNullList<ItemStack> items) {
    this.items = items;
  }

  public AbstractContainerMenu createMenu(final int containerId, final Inventory inventory) {
    return ChestMenu.threeRows(containerId, inventory, this);
  }

  @Override
  protected void saveAdditional(ValueOutput output) {
    super.saveAdditional(output);
    ContainerHelper.saveAllItems(output, this.items);
  }

  @Override
  protected void loadAdditional(ValueInput input) {
    super.loadAdditional(input);
    ContainerHelper.loadAllItems(input, this.items);
  }
}
